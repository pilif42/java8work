package com.philippe.app.service.mapper;

import com.philippe.app.domain.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomMapperServiceImpl implements CustomMapperService {

    private Function<User, example.avro.User> converter = this::mapToAvroContainer;

    public example.avro.User convert(final User user) {
        return converter.apply(user);
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
