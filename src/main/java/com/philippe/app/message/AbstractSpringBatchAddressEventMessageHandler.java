package com.philippe.app.message;

import com.philippe.app.domain.AddressEventMessage;
import com.philippe.app.domain.JobState;
import com.philippe.app.domain.JobStatus;
import com.philippe.app.exception.FatalJobException;
import com.philippe.app.exception.JobDisabledException;
import com.philippe.app.exception.JobException;
import com.philippe.app.exception.JobPausedException;
import com.philippe.app.service.mapper.ExceptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractSpringBatchAddressEventMessageHandler implements AddressEventMessageHandler {
    private final List<String> messageTypes;
    private final CrudRepository<JobStatus, String> jobStatusRepository;
    private final MessageSource messageSource;
    private final Validator validator;
    private final JobLauncher jobLauncher;
    private final Job job;
    private final RetryTemplate jobRetryTemplate;

    protected AbstractSpringBatchAddressEventMessageHandler(
            List<String> messageTypes,
            Validator validator, MessageSource messageSource, CrudRepository<JobStatus, String> jobStatusRepository,
            JobLauncher jobLauncher, Job job, RetryTemplate retryTemplate) {
        this.validator = validator;
        this.jobStatusRepository = jobStatusRepository;
        this.messageSource = messageSource;
        this.messageTypes = messageTypes;
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.jobRetryTemplate = retryTemplate;
    }

    @Override
    abstract public boolean handle(AddressEventMessage message) throws JobException;

    @Override
    public List<String> handlesTypes() {
        return messageTypes;
    }

    protected String getJobName() {
        return job.getName();
    }

    protected void checkJobState() throws JobException {
        String jobName = getJobName();
        Optional<JobStatus> status = jobStatusRepository.findById(jobName);
        if (status.isPresent()) {
            JobState state = status.get().getState();
            switch (state) {
                case DISABLED:
                    log.warn("Job {} is Disabled - Message will be removed", jobName);
                    throw new JobDisabledException(String.format("Job %s is Disabled", jobName));

                case PAUSED:
                    log.info("Job {} is Paused - Message will be retried", jobName);
                    throw new JobPausedException(String.format("Job %s is Paused", jobName));

                default:
                    break;
            }
        }
    }

    protected String buildValidationFailureMessage(Object supply, Errors errors) {
        StringBuilder builder = new StringBuilder();
        builder.append("Invalid ").append(getJobName()).append(" message received ").append(supply).append(":\n");
        for (FieldError error : errors.getFieldErrors()) {
            builder.append(messageSource.getMessage(error, Locale.getDefault())).append("\n");
        }
        return builder.toString();
    }

    protected Errors validate(Object message) {
        Errors errors = new BeanPropertyBindingResult(message, getJobName());
        validator.validate(message, errors);
        return errors;
    }

    protected boolean launchJob(JobParameters parameters) throws JobException {
        String jobName = getJobName();
        final JobExecution[] jobExecutions = new JobExecution[1];
        try {
            log.info("About to execute {}", jobName);
            jobRetryTemplate.execute(retryContext -> {
                jobExecutions[0] = jobLauncher.run(job, parameters);
                List<Throwable> throwableList = jobExecutions[0].getAllFailureExceptions();
                long jobId = jobExecutions[0].getJobId();
                if (!throwableList.isEmpty()) {
                    List<JobException> jobExceptionList = throwableList.stream().map(ExceptionMapper::convert).collect(Collectors.toList());
                    Optional<JobException> fatalJobExceptionOptional = jobExceptionList.stream().filter(FatalJobException.class::isInstance).findFirst();
                    if (fatalJobExceptionOptional.isPresent()) {
                        log.error("Job {} instance {} failed with fatal error: {}", jobName, jobId, fatalJobExceptionOptional.get());
                        throw fatalJobExceptionOptional.get();
                    } else {
                        log.warn("Job {} instance {} failed. Retrying...", jobName, jobId);
                        throw jobExceptionList.get(0);
                    }
                }
                return jobExecutions[0];
            });
            log.info("Completed execution of Job {} instance {}", jobName, jobExecutions[0].getJobId());
            return true;
        } catch (JobInstanceAlreadyCompleteException e) {
            log.info("Job {} is already complete", jobName, e);
            return true;
        } catch (FatalJobException fje) {
            throw fje;
        } catch (JobRestartException | JobParametersInvalidException | JobExecutionAlreadyRunningException e) {
            throw new JobException(String.format("Error attempting to start Job %s", jobName), e);
        } catch (JobException je) {
            log.error("Error running Job {} instance {} and all retries exhausted.", jobName, jobExecutions[0].getJobId(), je);
            throw je;
        } catch (Throwable e) {
            throw new JobException(String.format("Error running Job %s", jobName), e);
        }
    }
}
