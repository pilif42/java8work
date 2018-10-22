package com.philippe.app.util;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.philippe.app.util.JsonReaderUtil.beautifyJsonString;
import static org.junit.Assert.assertEquals;

public class JsonReaderUtilTest {


    private static final String JSON_FILE_PATH = "src/test/resources/json/sample.json";

    @Test
    public void testBeautifyJsonString() throws Exception {
        List<String> events = Files.readAllLines(Paths.get(JSON_FILE_PATH).toAbsolutePath());
        assertEquals(1, events.size());
        beautifyJsonString(events.get(0));
    }
}
