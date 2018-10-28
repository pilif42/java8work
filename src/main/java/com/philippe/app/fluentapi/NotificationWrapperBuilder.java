package com.philippe.app.fluentapi;

import com.philippe.app.domain.EventContext;
import com.philippe.app.domain.Notification;
import com.philippe.app.domain.NotificationContext;
import com.philippe.app.domain.NotificationWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NotificationWrapperBuilder {

    private NotificationWrapper notificationWrapper;
    private List<Notification> notificationList = new ArrayList<>();
    private EventContext eventContext;

    public NotificationWrapperBuilder() {
    }

    public EventContextBuilder eventContext(final String id, final String message) {
        Consumer<EventContext> consumer = contextData -> this.eventContext = contextData;
        return new EventContextBuilder(this, consumer, id, message);
    }

    public NotificationBuilder notificationList() {
        Consumer<Notification> consumer = notification -> notificationList.add(notification);
        return new NotificationBuilder(this, consumer);
    }

    public NotificationWrapper build() {
        if (notificationWrapper == null) {
            verifyData();
            buildNotificationWrapper();
        }
        return notificationWrapper;
    }

    private void verifyData() {
        if (notificationList.isEmpty()) {
            throw new IllegalArgumentException("Missing Notification data");
        }
    }

    private void buildNotificationWrapper() {
        notificationWrapper = new NotificationWrapper(buildNotificationContext());
        notificationWrapper.setEventContext(eventContext);
    }

    private NotificationContext buildNotificationContext() {
        return new NotificationContext(notificationList);
    }
}