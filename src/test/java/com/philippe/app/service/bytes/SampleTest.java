package com.philippe.app.service.bytes;

import com.google.common.primitives.Longs;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SampleTest {

    private final static String SOME_STR = "someString";
    private final static String SOME_LONG = "200";

    @Test
    public void byteArrayRepresentingString() {
        // GIVEN
        final byte[] array = SOME_STR.getBytes();

        // WHEN
        final String strValue = new String(array);
        assertEquals(SOME_STR, strValue);
        final byte[] result = strValue.getBytes();

        // THEN
        assertArrayEquals(array, result);
        assertEquals(SOME_STR, new String(result));
    }

    @Test
    public void byteArrayRepresentingLong() {
        // GIVEN
        final long amount = Long.parseLong(SOME_LONG);
        final byte[] array = Bytes.toBytes(amount);

        // WHEN
        final String strValue = new String(array);
        assertNotEquals(SOME_LONG, strValue);    // Note the NOT equal. So if dealing with a long value, we can NOT manipulate with new String(...)

        final Long longValue = Longs.fromByteArray(array);
        assertEquals(amount, longValue.longValue());

        final byte[] result = Longs.toByteArray(longValue);

        // THEN
        assertArrayEquals(array, result);
    }
}
