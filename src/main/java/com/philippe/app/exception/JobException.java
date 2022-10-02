package com.philippe.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobException extends Exception {
    private boolean retriable;

    public JobException(String message) {
        super(message);
        this.retriable = true;
    }

    public JobException(String message, Throwable e) {
        super(message, e);
        this.retriable = true;
    }
}
