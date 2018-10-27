package com.philippe.app.domain;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EventContext {
    private String id;
    private String type;
}
