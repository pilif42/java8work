package com.philippe.app.service.avro;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AvroCodecServiceImpl<T extends SpecificRecord> implements AvroCodecService<T> {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private SpecificDatumWriter<T> writer;
    private SpecificDatumReader<T> reader;
    private Class classOfT;

    AvroCodecServiceImpl(T avroObject) {
        writer = new SpecificDatumWriter<>(avroObject.getSchema());
        reader = new SpecificDatumReader<>(avroObject.getSchema());
        this.classOfT = avroObject.getClass();
    }

    @Override
    public byte[] encode(T message) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
        Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);

        writer.write(message, encoder);
        encoder.flush();
        byte[] encodedMessage = outputStream.toByteArray();
        outputStream.reset();
        return encodedMessage;
    }

    @Override
    public T decode(byte[] byteArray) throws InstantiationException, IllegalAccessException, IOException {
        T message = (T) classOfT.newInstance();

        // Reusing binaryDecoder with the right length of bytes.
        final DecoderFactory decoderFactory = DecoderFactory.get();
        BinaryDecoder decoder = decoderFactory.binaryDecoder(new byte[DEFAULT_BUFFER_SIZE],0,DEFAULT_BUFFER_SIZE, null);
        decoder = decoderFactory.binaryDecoder(byteArray, 0, byteArray.length, decoder);
        message = reader.read(message, decoder);
        return message;
    }
}
