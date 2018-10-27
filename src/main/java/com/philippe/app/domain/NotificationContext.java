package com.philippe.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class NotificationContext {
    private List<Notification> notificationList;
}
