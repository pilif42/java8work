package com.philippe.app.generics;

import com.philippe.app.domain.NotificationData;

public class NotificationDataBytePublisher implements BytePublisher<NotificationData> {
        @Override
        public byte[] send(NotificationData notificationData) {
                return new byte[0];     // TODO
        }
}
