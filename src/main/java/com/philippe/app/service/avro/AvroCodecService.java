package com.philippe.app.service.avro;

import org.apache.avro.specific.SpecificRecord;

import java.io.IOException;

public interface AvroCodecService<T extends SpecificRecord> {

    /**
     * Encodes an Avro object to a byte[].
     *
     * @param message - Avro object which needs to be encoded.
     * @return byte[] - serialized byte array.
     */
    byte[] encode(T message) throws IOException;

    /**
     * Decodes a byte[] to an Avro object.
     */
    T decode(byte[] byteArray) throws InstantiationException, IllegalAccessException, IOException;
}
