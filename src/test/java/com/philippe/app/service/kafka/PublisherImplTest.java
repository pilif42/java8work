package com.philippe.app.service.kafka;

import com.philippe.app.domain.SparkPocNotification;
import com.philippe.app.service.avro.AvroCodecService;
import com.philippe.app.service.mapper.CustomMapperService;
import example.avro.sparkpoc.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublisherImplTest {
    @InjectMocks
    private PublisherImpl publisher;

    @Mock
    private CustomMapperService customMapperService;

    @Mock
    private AvroCodecService<Notification> avroCodecServiceForNotifications;

    @Test
    public void testSendSparkPocNotification() throws IOException {
        // GIVEN
        final SparkPocNotification sparkPocNotification = mock(SparkPocNotification.class);
        final Notification notification = mock(Notification.class);
        when(customMapperService.convert(sparkPocNotification)).thenReturn(notification);

        final byte[] bytesToPublish = new byte[0];
        when(avroCodecServiceForNotifications.encode(notification)).thenReturn(bytesToPublish);

        // WHEN
        boolean result = publisher.send(sparkPocNotification);

        // THEN
        assertTrue(result);

        verify(customMapperService, times(1)).convert(sparkPocNotification);
        verify(avroCodecServiceForNotifications, times(1)).encode(notification);
    }
}
