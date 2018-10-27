package com.philippe.app.domain;

import lombok.Data;

@Data
public class NotificationWrapper {
    private NotificationContext notificationContext;
    private EventContext eventContext;
}
