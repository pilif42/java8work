package com.philippe.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Event {
    String eventType;
    String maturity;
}
