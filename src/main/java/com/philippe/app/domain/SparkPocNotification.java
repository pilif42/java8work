package com.philippe.app.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class SparkPocNotification {
    private String version;
    private String publishTime;
    private UUID id;
    private Item item;
    private Outcome outcome;
    private String deviceGuid;
}
