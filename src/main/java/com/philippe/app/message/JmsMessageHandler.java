package com.philippe.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.philippe.app.domain.AddressEventMessage;
import com.philippe.app.exception.JobException;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;

/**
 * JMS Message Handler
 * Initial handler for messages received from the JMS Queue.
 *
 * Parses the message to obtain the Event Type and uses that to select an appropriate
 * message-specific Handler (the first that announces it handles that type)
 *
 * Returns true if the message was handled successfully
 *
 */
@Component
public class JmsMessageHandler {
    private final List<AddressEventMessageHandler> handlers;

    private final AddressEventMessageParser parser;

    public JmsMessageHandler(List<AddressEventMessageHandler> handlers, AddressEventMessageParser parser) {
        this.handlers = handlers;
        this.parser = parser;
    }

    public boolean handle(Message jmsMessage) throws JsonProcessingException, JobException, JMSException {
        AddressEventMessage addressEvent = parser.parseJMSMessage(jmsMessage);

        if (addressEvent != null) {
            String eventType = addressEvent.getEventType();
            if (eventType != null) {
                for (AddressEventMessageHandler handler : handlers) {
                    if (handler.handlesTypes().contains(eventType)) {
                        return handler.handle(addressEvent);
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
