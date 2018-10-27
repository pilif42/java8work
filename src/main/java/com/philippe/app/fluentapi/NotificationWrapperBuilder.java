package com.philippe.app.fluentapi;

import com.philippe.app.domain.EventContext;
import com.philippe.app.domain.Notification;
import com.philippe.app.domain.NotificationContext;
import com.philippe.app.domain.NotificationWrapper;
import com.philippe.app.service.serialise.NotificationWrapperSerialiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class NotificationWrapperBuilder {

    private final Optional<NotificationWrapperSerialiser> serialiser;
    private NotificationWrapper notificationWrapper;
    private List<Notification> notificationList = new ArrayList<>();
    private EventContext eventContext;

    public NotificationWrapperBuilder() {
        this.serialiser = Optional.empty();
    }

    public NotificationWrapperBuilder(final NotificationWrapperSerialiser serialiser) {
        this.serialiser = Optional.of(serialiser);
    }

    public EventContextBuilder eventContext() {
        Consumer<EventContext> consumer = contextData -> this.eventContext = contextData;
        return new EventContextBuilder(this, consumer);
    }

    public NotificationBuilder notificationList() {
        Consumer<Notification> consumer = notification -> notificationList.add(notification);
        return new NotificationBuilder(this, consumer);
    }

    public NotificationWrapperSerialiser end() {
        build();
        return serialiser.orElse(null);
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
        notificationWrapper = new NotificationWrapper();
        notificationWrapper.setNotificationContext(buildNotificationContext());
        notificationWrapper.setEventContext(eventContext);
    }

    private NotificationContext buildNotificationContext() {
        NotificationContext notificationContext = new NotificationContext();
        notificationContext.setNotificationList(notificationList);
        return notificationContext;
    }
}