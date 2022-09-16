package com.philippe.app.service.mapper;

import com.philippe.app.exception.CustomException;

import java.util.function.Function;
import java.util.zip.ZipException;

public class ExceptionMapper {
    private final static Function<Throwable, CustomException> CONVERTER = ExceptionMapper::mapToJobException;

    public static CustomException convert(final Throwable inputException) {
        return CONVERTER.apply(inputException);
    }

    private static CustomException mapToJobException(final Throwable inputException) {
        if (inputException instanceof CustomException) {
            return (CustomException)inputException;
        }

        // TODO Using NullPointerException for the example but the Spring Batch ItemStreamException would make more sense.
        if (inputException instanceof NullPointerException) {
            NullPointerException nullPointerException = (NullPointerException)inputException;
            Throwable cause = nullPointerException.getCause();
            if (cause instanceof ZipException) {
                return new CustomException(CustomException.Fault.VALIDATION_FAILED, cause.getMessage());
            }
        }
        return new CustomException(CustomException.Fault.SYSTEM_ERROR,  inputException.getMessage());
    }
}
