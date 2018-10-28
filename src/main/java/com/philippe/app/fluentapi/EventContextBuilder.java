package com.philippe.app.fluentapi;

import com.philippe.app.domain.EventContext;

import java.util.function.Consumer;

public class EventContextBuilder {
    private final NotificationWrapperBuilder parentBuilder;
    private final Consumer<EventContext> callback;
    private EventContext eventContextInstance;

    public EventContextBuilder(final NotificationWrapperBuilder parentBuilder, final Consumer<EventContext> callback, final String id, final String message) {
        this.parentBuilder = parentBuilder;
        this.callback = callback;
        this.eventContextInstance = new EventContext(id, message);
    }

    public EventContextBuilder addType(final String type) {
        eventContextInstance.setType(type);
        return this;
    }

    public NotificationWrapperBuilder end() {
        callback.accept(eventContextInstance);
        return parentBuilder;
    }
}