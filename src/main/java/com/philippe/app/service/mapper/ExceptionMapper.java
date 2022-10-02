package com.philippe.app.service.mapper;

import com.philippe.app.exception.FatalJobException;
import com.philippe.app.exception.JobException;

import java.util.function.Function;
import java.util.zip.ZipException;

public class ExceptionMapper {
    private final static Function<Throwable, JobException> CONVERTER = ExceptionMapper::mapToJobException;

    public static JobException convert(final Throwable inputException) {
        return CONVERTER.apply(inputException);
    }

    private static JobException mapToJobException(final Throwable inputException) {
        if (inputException instanceof JobException) {
            return (JobException)inputException;
        }

        // TODO Using NullPointerException for the example but the Spring Batch ItemStreamException would make more sense.
        if (inputException instanceof NullPointerException) {
            NullPointerException nullPointerException = (NullPointerException)inputException;
            Throwable cause = nullPointerException.getCause();
            if (cause instanceof ZipException) {
                return new FatalJobException(cause.getMessage());
            }
        }
        return new JobException(inputException.getMessage());
    }
}
