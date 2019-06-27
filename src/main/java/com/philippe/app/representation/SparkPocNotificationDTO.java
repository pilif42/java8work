package com.philippe.app.representation;

import com.philippe.app.domain.Item;
import com.philippe.app.domain.Outcome;
import lombok.Data;

import java.util.UUID;

@Data
public class SparkPocNotificationDTO {
    private UUID id;

    private String version;
    private String publishTime;
    private Item item;
    private Outcome outcome;
    private String deviceGuid;
}
