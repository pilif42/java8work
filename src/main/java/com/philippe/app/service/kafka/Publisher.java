package com.philippe.app.service.kafka;

import com.philippe.app.domain.SparkPocNotification;
import com.philippe.app.domain.User;

public interface Publisher {
    boolean send(final User user);
    boolean send(final SparkPocNotification sparkPocNotification);
}
