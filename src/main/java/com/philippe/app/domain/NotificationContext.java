package com.philippe.app.domain;

import lombok.Data;

import java.util.List;

/**
 * A NotificationContext with 1 mandatory field: notificationList.
 */
@Data
public class NotificationContext {
    private final List<Notification> notificationList;
}
