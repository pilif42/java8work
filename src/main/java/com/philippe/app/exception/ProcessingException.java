package com.philippe.app.exception;

public class ProcessingException extends RuntimeException {
    public ProcessingException(String errorMessage) {
        super(errorMessage);
    }
    public ProcessingException(String errorMessage, RuntimeException e) {
        super(errorMessage, e);
    }
}
