package com.philippe.app.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressEventMessage {
    private String eventType;
    private Object data;    // TODO
}
