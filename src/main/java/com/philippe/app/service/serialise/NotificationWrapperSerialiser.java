package com.philippe.app.service.serialise;

import com.philippe.app.domain.NotificationWrapper;

public interface NotificationWrapperSerialiser {
    byte[] serialise(NotificationWrapper notificationWrapper);
}
