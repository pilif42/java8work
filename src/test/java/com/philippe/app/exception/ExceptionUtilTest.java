package com.philippe.app.exception;

import org.apache.avro.generic.GenericData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExceptionUtilTest {
    private static final String EXCEPTION_CREATED_X_TIME = "%s (called %d times)";
    private static final String ERROR_MSG_1 = "1st error";
    private static final String ERROR_MSG_2 = "2nd error";

    @Test
    public void testConsolidate_ThreeExceptions_TwoWithIdenticalMsg() {
        // Given
        final Set<Exception> inputSet = getExceptionSet(ERROR_MSG_1, ERROR_MSG_2, ERROR_MSG_1);

        // When
        final Set<ComparableException> outputSet = ExceptionUtil.consolidate(inputSet);

        // Then
        assertEquals(2, outputSet.size());

        final List<String> msgList = new ArrayList<>();
        final Iterator<ComparableException> iterator = outputSet.iterator();
        while (iterator.hasNext()) {
            msgList.add(iterator.next().getMessage());
        }
        assertTrue(msgList.containsAll(Arrays.asList(ERROR_MSG_2, format(EXCEPTION_CREATED_X_TIME, ERROR_MSG_1, 2))));
    }

    private Set<Exception> getExceptionSet(final String... errMsgs) {
        final Set<Exception> exceptionSet = new HashSet<>();
        for (String err : errMsgs) {
            final Exception exception = new Exception(err, new NullPointerException());
            exceptionSet.add(exception);
        }
        return exceptionSet;
    }
}
