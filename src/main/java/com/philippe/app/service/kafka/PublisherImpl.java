package com.philippe.app.service.kafka;

import com.philippe.app.domain.User;
import com.philippe.app.service.avro.AvroCodecService;
import com.philippe.app.service.avro.AvroCodecServiceFactory;
import com.philippe.app.service.avro.AvroCodecServicePool;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PublisherImpl implements Publisher {

    @Qualifier("beanMapper")
    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public boolean send(final User user) {
        final example.avro.User avroUser = mapperFacade.map(user, example.avro.User.class);

        AvroCodecServiceFactory<example.avro.User> factoryForUsers = new AvroCodecServiceFactory<>(new example.avro.User());
        AvroCodecServicePool<example.avro.User> poolForUsers = new AvroCodecServicePool<>(factoryForUsers);
        poolForUsers.setMaxTotal(10);

        boolean isEncoded = true;

        try {
            AvroCodecService<example.avro.User> avroCodecUtil = poolForUsers.borrowObject();
            avroCodecUtil.encode(avroUser);
        } catch (Exception ex) {
        }

        // TODO Publish

        return true;
    }
}
