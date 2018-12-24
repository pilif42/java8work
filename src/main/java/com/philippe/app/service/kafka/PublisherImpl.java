package com.philippe.app.service.kafka;

import com.philippe.app.domain.User;
import com.philippe.app.service.avro.AvroCodecService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherImpl implements Publisher {

    @Qualifier("beanMapper")
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private AvroCodecService<example.avro.User> avroCodecServiceForUsers;

    @Override
    public boolean send(final User user) {
        final example.avro.User avroUser = mapperFacade.map(user, example.avro.User.class);
        // TODO id is mapped to empty string

        boolean isEncoded = false;

        byte[] byteArray = null;
        try {
            byteArray = avroCodecServiceForUsers.encode(avroUser);
        } catch (Exception ex) {
            // TODO We come here at the moment as id is null
            log.debug("exception is {}", ex);
        }

        log.debug("byteArray is {}", byteArray);
        // TODO Publish the byteArray to Kafka

        return isEncoded;
    }
}
