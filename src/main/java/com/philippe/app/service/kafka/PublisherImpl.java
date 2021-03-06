package com.philippe.app.service.kafka;

import com.philippe.app.domain.SparkPocNotification;
import com.philippe.app.domain.User;
import com.philippe.app.service.avro.AvroCodecService;
import com.philippe.app.service.mapper.CustomMapperService;
import example.avro.sparkpoc.Notification;
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

    @Autowired
    private AvroCodecService<example.avro.sparkpoc.Notification> avroCodecServiceForNotifications;

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

    @Override
    public boolean send(SparkPocNotification sparkPocNotification) {
        final Notification notification = customMapperService.convert(sparkPocNotification);
        boolean isEncoded = false;

        byte[] byteArray = null;
        try {
            byteArray = avroCodecServiceForNotifications.encode(notification);
            isEncoded = true;
        } catch (Exception ex) {
            log.debug("exception is {}", ex);
        }

        log.debug("byteArray is {}", byteArray);
        // TODO Publish the byteArray to Kafka. Choose between:
        // TODO https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent
        // TODO https://www.baeldung.com/spring-kafka
        // TODO https://codenotfound.com/spring-kafka-apache-avro-serializer-deserializer-example.html

        return isEncoded;
    }
}
