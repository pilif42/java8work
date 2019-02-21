package com.philippe.app.generics;

import com.philippe.app.domain.CommonData;
import com.philippe.app.domain.RecordType;

/**
 *
 * Thanks to the (U) below, we can write:
 * final NotificationDataBytePublisher notificationPublisher = PublisherFactory.getBytePublisher(RecordType.NOTIFICATION);
 * ie without the casting (NotificationDataBytePublisher) in front of PublisherFactory.
 *
 * Also note that it is U extends BytePublisher... and NOT implement.
 *
 */
public class PublisherFactory {
    public static <U extends BytePublisher<V>, V extends CommonData> U getBytePublisher(final RecordType recordType) {
        switch(recordType) {
            case NOTIFICATION:
                return (U) new NotificationDataBytePublisher();
            case ACK:
                return (U) new AckDataBytePublisher();
            default:
                throw new UnsupportedOperationException("Unsupported record type.");
        }

    }
}
