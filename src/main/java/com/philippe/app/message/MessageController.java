package com.philippe.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.philippe.app.exception.JobException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;

/**
 * This is the entry point for the message-related classes.
 */
@Slf4j
@AllArgsConstructor
@Component
public class MessageController {
    private final JmsMessageHandler handler;

    @JmsListener(destination = "${dataload.servicebus.queue}")
    @Transactional(transactionManager = "jmsTransactionManager")
    public void receiveMessage(Message message) throws JMSException, JsonProcessingException {
        log.info("Received Message");
        try {
            boolean handled = handler.handle(message);
            if (!handled) {
                throw new JMSException("Message was not handled");
            }
        } catch (JobException e) {
            final String msg = e.getMessage();
            if (e.isRetriable()) {
                log.info(msg + " - retrying");
                throw new JMSException(msg);
            } else {
                log.info(msg + " - Removing message from queue");
            }
        }
    }
}
