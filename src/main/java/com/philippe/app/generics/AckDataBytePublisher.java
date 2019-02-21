package com.philippe.app.generics;

import com.philippe.app.domain.AckData;

public class AckDataBytePublisher implements BytePublisher<AckData> {
    @Override
    public byte[] send(AckData ackData) {
        return new byte[0]; // TODO
    }
}
