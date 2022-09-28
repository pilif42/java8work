package com.philippe.app.fluentapi;

import com.philippe.app.domain.EventContext;
import com.philippe.app.domain.Notification;
import com.philippe.app.domain.NotificationContext;
import com.philippe.app.domain.NotificationWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleUsageTest {

    private final String EVENT_ID = "70930c97-c862-43cf-ae54-57f2b5b12ab1";
    private final String EVENT_MSG = "{\"widget\":{\"debug\":\"on\",\"window\":{\"title\":\"Sample Konfabulator Widget\",\"name\":\"main_window\",\"width\":500,\"height\":500},\"image\":{\"src\":\"Images/Sun.png\",\"name\":\"sun1\",\"hOffset\":250,\"vOffset\":250,\"alignment\":\"center\"},\"text\":{\"data\":\"Click Here\",\"size\":36,\"style\":\"bold\",\"name\":\"text1\",\"hOffset\":250,\"vOffset\":100,\"alignment\":\"center\",\"onMouseUp\":\"sun1.opacity = (sun1.opacity / 100) * 90;\"}}}";
    private final String EVENT_TYPE = "TX";

    private final String NOTIF_CODE_1 = "01";
    private final String NOTIF_DESC_1 = "desc 01";
    private final String NOTIF_ACT_1 = "act 01";

    private final String NOTIF_CODE_2 = "02";
    private final String NOTIF_DESC_2 = "desc 02";

    @Test
    public void buildNotificationWrapper() {
        final NotificationWrapper notificationWrapper = new NotificationWrapperBuilder()
                .eventContext(EVENT_ID, EVENT_MSG)
                .addType(EVENT_TYPE)
                .end()
                .notificationList()
                .addNotification(NOTIF_CODE_1, NOTIF_DESC_1).addAction(NOTIF_ACT_1)
                .addNotification(NOTIF_CODE_2, NOTIF_DESC_2)
                .end()
                .build();

        final EventContext eventContext = notificationWrapper.getEventContext();
        assertEquals(EVENT_ID, eventContext.getId());
        assertEquals(EVENT_MSG, eventContext.getMessage());
        assertEquals(EVENT_TYPE, eventContext.getType());

        final NotificationContext notificationContext = notificationWrapper.getNotificationContext();
        final List<Notification> notifList = notificationContext.getNotificationList();
        assertEquals(2, notifList.size());
        final List<Notification> expectedList = new ArrayList<>();
        final Notification notif1 = new Notification(NOTIF_CODE_1, NOTIF_DESC_1);
        notif1.setAction(NOTIF_ACT_1);
        expectedList.add(notif1);
        final Notification notif2 = new Notification(NOTIF_CODE_2, NOTIF_DESC_2);
        expectedList.add(notif2);
        assertTrue(notifList.containsAll(expectedList));
    }
}
