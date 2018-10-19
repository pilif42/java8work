package com.philippe.app.util;

import com.philippe.app.domain.Event;

import java.util.function.BiPredicate;

public class FilteringUtil {

    public static final String NATIVE = "native";

    public static final BiPredicate<Event, String[]> filterByEventType =
            (event, eventTypeArray) -> {
                boolean filter = false;
                for (String eventType: eventTypeArray){
                    if (eventType.equals(event.getEventType())) {
                        filter = true;
                        break;
                    }
                }
                return filter;
            };

    public static boolean hasNativeMaturity(final Event event) {
        return event.getMaturity().equals(NATIVE);
    }
}
