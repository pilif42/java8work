package com.philippe.app.service.mapper;

import com.philippe.app.domain.Item;
import com.philippe.app.domain.SparkPocNotification;
import com.philippe.app.domain.User;
import example.avro.sparkpoc.ItemType;
import example.avro.sparkpoc.Notification;
import example.avro.sparkpoc.Outcome;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomMapperServiceImpl implements CustomMapperService {

    private Function<User, example.avro.User> converter = this::mapToAvroContainer;

    public example.avro.User convert(final User user) {
        return converter.apply(user);
    }

    @Override
    public Notification convert(SparkPocNotification sparkPocNotification) {
        final Notification notification = new Notification();
        notification.setVersion(sparkPocNotification.getVersion());
        notification.setPublishTime(sparkPocNotification.getPublishTime());
        notification.setId(sparkPocNotification.getId().toString());
        final Item item = sparkPocNotification.getItem();
        notification.setItem(example.avro.sparkpoc.Item.newBuilder().setGuid(item.getGuid()).setType(ItemType.valueOf(item.getItemType().name())).build());
        notification.setOutcome(Outcome.valueOf(sparkPocNotification.getOutcome().name()));
        notification.setDeviceGuid(sparkPocNotification.getDeviceGuid());
        return notification;
    }

    private example.avro.User mapToAvroContainer(final User user) {
        final example.avro.User avroContainer = new example.avro.User();
        avroContainer.setId(user.getId().toString());
        avroContainer.setName(user.getName());
        avroContainer.setFavoriteColor(user.getFavoriteColor());
        avroContainer.setFavoriteNumber(user.getFavoriteNumber());
        return avroContainer;
    }
}
