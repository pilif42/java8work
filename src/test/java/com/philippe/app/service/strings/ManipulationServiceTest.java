package com.philippe.app.service.strings;

import com.philippe.app.service.strings.impl.ManipulationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ManipulationServiceTest {

    private final static String JSON = "JSON";
    private final static String TXT = "TXT";
    private final static String URL_HTTPS_1 = "https://www.bbc.co.uk:443";
    private final static String URL_HTTPS_2 = "https://www.asse.fr:443";
    private final static String URL_HTTP_1 = "httP://www.bbc.co.uk:8080";
    private final static String URL_HTTP_2 = "http://www.asse.fr:8080";

    @InjectMocks
    private ManipulationServiceImpl manipulationService;

    @Test
    public void happyPath() {
        Map<String, String> result = manipulationService.process("TXT=/opt/app/data/sample.txt,/opt/app/data/sample_1.txt|JSON=/opt/app/data/a.json,/opt/app/data/b.json");

        Set<String> theKeys = result.keySet();
        assertEquals(2, theKeys.size());
        assertTrue(theKeys.contains(JSON));
        assertTrue(theKeys.contains(TXT));

        assertEquals("/opt/app/data/a.json,/opt/app/data/b.json", result.get(JSON));
        assertEquals("/opt/app/data/sample.txt,/opt/app/data/sample_1.txt", result.get(TXT));
    }

    @Test
    public void emptyString() {
        assertNull(manipulationService.process(""));
    }

    @Test
    public void filterUrls_happyPath() {
        List<String> inputList = Arrays.asList(URL_HTTPS_1, URL_HTTPS_2);
        assertEquals(URL_HTTPS_1, manipulationService.filterUrls(inputList).get());
    }

    @Test
    public void filterUrls_noHttpsUrl() {
        List<String> inputList = Arrays.asList(URL_HTTP_1, URL_HTTP_2);
        assertEquals(Optional.empty(), manipulationService.filterUrls(inputList));
    }
}
