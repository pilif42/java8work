package com.philippe.app.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ComparableException extends Exception {

    public ComparableException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ComparableException)) {
            return false;
        }

        ComparableException that = (ComparableException)o;
        return new EqualsBuilder()
                .append(getMessage(), that.getMessage())
                .append(getStackTrace(), that.getStackTrace())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getMessage())
                .append(getStackTrace())
                .toHashCode();
    }
}
