package com.philippe.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AddressEventMessage {
    private String eventType;
    private Map<String, String> data;
}
