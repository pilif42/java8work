package com.philippe.app.service.avro;

import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class AvroCodecServicePool<T extends SpecificRecord> extends GenericObjectPool<AvroCodecService<T>> {

    public AvroCodecServicePool(PooledObjectFactory<AvroCodecService<T>> factory) {
        super(factory);
    }

    public AvroCodecServicePool(PooledObjectFactory<AvroCodecService<T>> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
}