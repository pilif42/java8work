package com.philippe.app.domain;

import lombok.Data;

/**
 * An EventContext with 2 mandatory fields: id and message, 1 optional field: type.
 */
@Data
public class EventContext {
    private final String id;
    private final String message;
    private String type;
}
