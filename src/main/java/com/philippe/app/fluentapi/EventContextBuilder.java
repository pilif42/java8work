package com.philippe.app.fluentapi;

import com.philippe.app.domain.EventContext;

import java.util.function.Consumer;

public class EventContextBuilder {
    private final NotificationWrapperBuilder parentBuilder;
    private final Consumer<EventContext> callback;
    private EventContext eventContextInstance;

    public EventContextBuilder(final NotificationWrapperBuilder parentBuilder, final Consumer<EventContext> callback) {
        this.parentBuilder = parentBuilder;
        this.callback = callback;
        this.eventContextInstance = EventContext.builder().build();
    }

    public EventContextBuilder addId(final String id) {
        eventContextInstance.setId(id);
        return this;
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