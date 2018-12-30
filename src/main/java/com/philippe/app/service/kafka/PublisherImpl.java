package com.philippe.app.service.kafka;

import com.philippe.app.domain.User;
import com.philippe.app.service.avro.AvroCodecService;
import com.philippe.app.service.mapper.CustomMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherImpl implements Publisher {

    // TODO The Orika mapper would not map correctly the String id from app.domain.User to the UUID id of example.avro.User avroUser
    // TODO So we went for a CustomMapperService.
//    @Qualifier("beanMapper")
//    @Autowired
//    private MapperFacade mapperFacade;

    @Autowired
    private CustomMapperService customMapperService;

    @Autowired
    private AvroCodecService<example.avro.User> avroCodecServiceForUsers;

    @Override
    public boolean send(final User user) {
        final example.avro.User avroUser = customMapperService.convert(user);

        boolean isEncoded = false;

        byte[] byteArray = null;
        try {
            byteArray = avroCodecServiceForUsers.encode(avroUser);
            isEncoded = true;
        } catch (Exception ex) {
            log.debug("exception is {}", ex);
        }

        log.debug("byteArray is {}", byteArray);
        // TODO Publish the byteArray to Kafka

        return isEncoded;
    }
}
