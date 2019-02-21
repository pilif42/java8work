package com.philippe.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.ByteBuffer;

@Data @AllArgsConstructor
public class IndexWrapper {
    private ByteBuffer primaryRowId;
}
