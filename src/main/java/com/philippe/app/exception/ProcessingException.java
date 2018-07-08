package com.philippe.app.exception;

public class ProcessingException extends Exception {
    public ProcessingException(String errorMessage, RuntimeException e) {
        super(errorMessage, e);
    }
}
