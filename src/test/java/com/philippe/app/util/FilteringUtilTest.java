package com.philippe.app.util;

import com.philippe.app.domain.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.philippe.app.util.FilteringUtil.NATIVE;
import static com.philippe.app.util.FilteringUtil.filterByEventType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilteringUtilTest {

    private static final String DISPUTE_TYPE = "dispute";
    private static final String SETTL_TYPE = "settlement";
    private static final String TX_TYPE = "tx";

    @Test
    public void testHasNativeMaturity() {
        final List<Event> filteredList = buildInputList().stream()
                .filter(FilteringUtil::hasNativeMaturity)
                .collect(Collectors.toList());

        // Then
        assertEquals(3, filteredList.size());
    }

    @Test
    public void testFilterByEventType() {
        // Given
        final String[] eventTypes = new String[]{TX_TYPE, SETTL_TYPE, DISPUTE_TYPE};

        // When
        final List<Event> filteredList = buildInputList().stream()
                .filter(event -> filterByEventType.test(event, eventTypes))
                .collect(Collectors.toList());

        // Then
        assertEquals(4, filteredList.size());
    }

    private static List<Event> buildInputList() {
        final List<Event> inputList = new ArrayList<>();
        inputList.add(new Event(TX_TYPE, NATIVE));
        inputList.add(new Event(TX_TYPE, NATIVE));
        inputList.add(new Event(TX_TYPE, "enriched"));
        inputList.add(new Event(SETTL_TYPE, "enriched"));
        inputList.add(new Event("dummy", NATIVE));
        return inputList;
    }
}
