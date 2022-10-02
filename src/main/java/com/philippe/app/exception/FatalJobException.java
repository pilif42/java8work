package com.philippe.app.exception;

public class FatalJobException extends JobException {
    public FatalJobException(String message) {
        super(message);
        setRetriable(false);
    }
}
