package com.philippe.app.domain;

import lombok.Data;

@Data
public class Notification {
    private final String code;
    private final String description;
    private String action;
}
