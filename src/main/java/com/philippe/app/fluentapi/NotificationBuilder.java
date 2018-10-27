package com.philippe.app.fluentapi;

import com.philippe.app.domain.Notification;

import java.util.function.Consumer;

public class NotificationBuilder {

    private static final String ERROR_MSG = "Missing code and description - please use addNotification method of " +
            "NotificationBuilder first to add %s";

    private final NotificationWrapperBuilder parentBuilder;
    private final Consumer<Notification> callback;
    private Notification notificationInstance;

    public NotificationBuilder(final NotificationWrapperBuilder parentBuilder,
                                   final Consumer<Notification> callback) {
        this.parentBuilder = parentBuilder;
        this.callback = callback;
    }

    /**
     * This method to define the mandatory fields in a Notification.
     */
    public NotificationBuilder addNotification(final String code, final String description) {
        notificationInstance = new Notification(code, description);
        callback.accept(notificationInstance);
        return this;
    }

    /**
     * This method to define an optional field in a Notification.
     */
    public NotificationBuilder addAction(String action) {
        if (notificationInstance == null) {
            throw new IllegalArgumentException(String.format(ERROR_MSG, "action"));
        }
        notificationInstance.setAction(action);
        return this;
    }

    public NotificationWrapperBuilder end() {
        return parentBuilder;
    }
}