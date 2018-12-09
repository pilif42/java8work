package com.philippe.app.service.kafka;

import com.philippe.app.domain.User;
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

        // TODO Encode to bytes with Avro
        // TODO Publish

        return true;
    }
}
