package com.philippe.app.message;

import com.philippe.app.domain.AddressEventMessage;
import com.philippe.app.exception.JobException;

import java.util.List;

public interface AddressEventMessageHandler {
    boolean handle(AddressEventMessage message) throws JobException;
    List<String> handlesTypes();
}
