package com.philippe.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philippe.app.domain.AddressEventMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddressEventMessageParser {
    private final ObjectMapper objectMapper;

    public AddressEventMessageParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AddressEventMessage parseJMSMessage(Message message) throws JMSException, JsonProcessingException {
        return objectMapper.readValue(message.getBody(String.class), AddressEventMessage.class);
    }

    public <T> T parseAddressEventData(AddressEventMessage event, Class<T> clazz) {
        return objectMapper.convertValue(event.getData(), clazz);
    }
}
