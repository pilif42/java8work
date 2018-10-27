package com.philippe.app.fluentapi;

import com.philippe.app.domain.NotificationWrapper;
import com.philippe.app.service.serialise.NotificationWrapperSerialiser;
import com.philippe.app.service.serialise.NotificationWrapperSerialiserImpl;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SampleUsageTest {

    private final String EVENT_ID = "70930c97-c862-43cf-ae54-57f2b5b12ab1";
    private final String EVENT_TYPE = "TX";

    private final String NOTIF_CODE_1 = "01";
    private final String NOTIF_DESC_1 = "desc 01";
    private final String NOTIF_ACT_1 = "act 01";

    private final String NOTIF_CODE_2 = "02";
    private final String NOTIF_DESC_2 = "desc 02";

    @Test
    public void serialiseNotificationWrapper() {
        final NotificationWrapper notificationWrapper = new NotificationWrapperBuilder()
                .eventContext()
                .addId(EVENT_ID).addType(EVENT_TYPE)
                .end()
                .notificationList()
                .addNotification(NOTIF_CODE_1, NOTIF_DESC_1).addAction(NOTIF_ACT_1)
                .addNotification(NOTIF_CODE_2, NOTIF_DESC_2)
                .end()
                .build();

        final NotificationWrapperSerialiser notificationWrapperSerialiser = new NotificationWrapperSerialiserImpl();
        assertNotNull(notificationWrapperSerialiser.serialise(notificationWrapper));
    }
}
