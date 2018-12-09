package com.philippe.app.exception;

public class CustomException extends Exception {

    public enum Fault {
        SYSTEM_ERROR,
        RESOURCE_NOT_FOUND,
        RESOURCE_VERSION_CONFLICT,
        VALIDATION_FAILED,
        ACCESS_DENIED,
        BAD_REQUEST
    }

    private Fault fault;

    public CustomException(final Fault aFault, final String message) {
        super(message);
        fault = aFault;
    }

    public CustomException(final Fault aFault, final String message, final Throwable cause) {
        super(message, cause);
        fault = aFault;
    }

    public final Fault getFault() {
        return fault;
    }
}
