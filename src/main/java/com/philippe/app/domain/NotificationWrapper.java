package com.philippe.app.domain;

import lombok.Data;

/**
 * An object to wrap a NotificationContext (mandatory - must contain at least one Notification) and its associated optional EventContext.
 */
@Data
public class NotificationWrapper {
    private final NotificationContext notificationContext;
    private EventContext eventContext;
}
