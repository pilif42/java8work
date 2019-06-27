package com.philippe.app.service.mapper;

import com.philippe.app.domain.SparkPocNotification;
import com.philippe.app.domain.User;
import example.avro.sparkpoc.Notification;

public interface CustomMapperService {
    example.avro.User convert(final User user);
    Notification convert(final SparkPocNotification sparkPocNotification);
}
