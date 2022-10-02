package com.philippe.app.message;

import com.philippe.app.domain.AddressEventMessage;
import com.philippe.app.domain.JobStage;
import com.philippe.app.domain.JobStatus;
import com.philippe.app.domain.LoadFullSupply;
import com.philippe.app.domain.SupplyType;
import com.philippe.app.exception.FatalJobException;
import com.philippe.app.exception.JobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component
@Slf4j
public class LoadFullSupplyMessageHandler extends AbstractSpringBatchAddressEventMessageHandler {

    private final AddressEventMessageParser parser;
    private final String fullSupplyLoadSchema;
    private final Boolean initiateNextJob;

    public LoadFullSupplyMessageHandler(CrudRepository<JobStatus, String> jobStatusRepository,
                                        LoadFullSupplyValidator validator,
                                        MessageSource fullSupplyMessageSource,
                                        AddressEventMessageParser parser,
                                        JobLauncher jobLauncher,
                                        Job loadFullSupplyJob,
                                        @Value("${dataload.database.dataload.fullsupply.schema}") String fullSupplyLoadSchema,
                                        RetryTemplate defaultJobRetryTemplate,
                                        @Value("${batch.job.loadfullsupply.initiatenextjob}") Boolean initiateNextJob) {
        super(Collections.singletonList(LoadFullSupply.EVENT_TYPE),
                validator,
                fullSupplyMessageSource,
                jobStatusRepository,
                jobLauncher,
                loadFullSupplyJob,
                defaultJobRetryTemplate);
        this.parser = parser;
        this.fullSupplyLoadSchema = fullSupplyLoadSchema;
        this.initiateNextJob = initiateNextJob;
    }

    @Override
    public boolean handle(AddressEventMessage message) throws JobException {
        String jobName = getJobName();
        log.info("Handling {} Message", jobName);
        LoadFullSupply supply = parser.parseAddressEventData(message, LoadFullSupply.class);
        Errors errors = validate(supply);

        boolean isValid = !errors.hasErrors();
        if (!isValid) {
            String validationMessage = buildValidationFailureMessage(supply, errors);
            log.error(validationMessage);
            throw new FatalJobException(validationMessage);
        } else {
            checkJobState();
            Boolean initiateNextJob = this.initiateNextJob;
            if (null != supply.getInitiateNextJob()){
                initiateNextJob = supply.getInitiateNextJob();
            }

            String extractionDate = supply.getExtractionDate().format(DateTimeFormatter.ISO_DATE);
            JobParameters parameters = new JobParametersBuilder()
                    .addString(JobParameterNames.FILE_LOCATION_GB_KEY, supply.getFileLocationGB(), false)
                    .addString(JobParameterNames.FILE_LOCATION_ISL_KEY, supply.getFileLocationISL(), false)
                    .addString(JobParameterNames.EXTRACTION_DATE_KEY, extractionDate, true)
                    .addString(JobParameterNames.SOURCE_KEY, supply.getSource(), true)
                    .addString(JobParameterNames.LOAD_SCHEMA_NAME_KEY, fullSupplyLoadSchema)
                    .addString(JobParameterNames.SUPPLY_TYPE_KEY, SupplyType.FULL.toString())
                    .addString(JobParameterNames.JOB_STAGE_KEY, JobStage.LOAD.toString())
                    .addString(JobParameterNames.INITIATE_NEXT_JOB_KEY, initiateNextJob.toString())
                    .toJobParameters();
            return launchJob(parameters);
        }
    }
}

