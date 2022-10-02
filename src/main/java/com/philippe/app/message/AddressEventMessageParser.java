package com.philippe.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.philippe.app.domain.AddressEventMessage;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class AddressEventMessageParser {
    public AddressEventMessage parseJMSMessage(Message jmsMessage) throws JsonProcessingException {
        // TODO
        return null;
    }


}
