package com.philippe.app.generics;

import com.philippe.app.domain.CommonData;

public interface BytePublisher <T extends CommonData> {
    byte[] send(final T t);
}
