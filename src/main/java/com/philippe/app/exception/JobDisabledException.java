package com.philippe.app.exception;

public class JobDisabledException extends FatalJobException {
    public JobDisabledException(String message) {
       super(message);
    }
}
