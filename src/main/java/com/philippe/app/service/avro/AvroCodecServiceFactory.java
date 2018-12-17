package com.philippe.app.service.avro;

import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class AvroCodecServiceFactory<T extends SpecificRecord> extends BasePooledObjectFactory<AvroCodecService<T>> {

    private T clazzOfT;

    public AvroCodecServiceFactory(T clazzOfT) {
        this.clazzOfT = clazzOfT;
    }

    @Override
    public AvroCodecService<T> create() {
        return new AvroCodecServiceImpl<>(this.clazzOfT);
    }

    @Override
    public PooledObject<AvroCodecService<T>> wrap(AvroCodecService<T> obj) {
        return new DefaultPooledObject<>(obj);
    }
}