package com.philippe.app.representation;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatedSparkPocNotificationDTO {
    private UUID id;
    private boolean created;
}
