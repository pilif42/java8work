package com.philippe.app.service.strings;

import com.philippe.app.service.strings.impl.ManipulationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ManipulationServiceTest {

    private final static String JSON = "JSON";
    private final static String TXT = "TXT";

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
}
