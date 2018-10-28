package com.philippe.app.domain;

import lombok.Data;

/**
 * A Notification with 2 mandatory fields: code and description, 1 optional field: action.
 */
@Data
public class Notification {
    private final String code;
    private final String description;
    private String action;
}
