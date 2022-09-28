package com.philippe.app.service.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.philippe.app.service.json.JsonServiceImpl.getJsonNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonServiceImplTest {

    private final static String INITIAL_STATE = "{\"version\":\"1.0.0\",\"publishTime\":\"2009-12-31T23:59:59\",\"deviceGuid\":\"123e4567-e89b-42d3-a456-556642440999\",\"item\":{\"guid\":\"123e4567-e89b-42d3-a456-556642440666\",\"itemType\":\"LETTER\"},\"outcome\":\"DELIVERED\"}";
    private final static String NEW_STATE = "{\"version\":\"1.0.0\",\"publishTime\":\"2009-12-31T23:59:59\",\"deviceGuid\":\"123e4567-e89b-42d3-a456-556642440999\",\"item\":{\"guid\":\"123e4567-e89b-42d3-a456-556642440666\",\"itemType\":\"LETTER\",\"itemWeight\":\"1kg\"},\"outcome\":\"DELIVERED\"}";
    private final static String PATCH = "[{\"op\":\"add\",\"path\":\"/item/itemWeight\",\"value\":\"1kg\"}]";

    private JsonService jsonService;

    @BeforeEach
    public void setUp() {
        jsonService = new JsonServiceImpl();
    }

    @Test
    public void testProvideDifferences() throws IOException {
        // WHEN
        final JsonNode result = jsonService.provideDifferences(INITIAL_STATE, NEW_STATE);

        // THEN
        assertEquals(PATCH, result.toString());
    }

    @Test
    public void testApplyPatch() throws IOException, JsonPatchException {
        // WHEN
        final JsonNode result = jsonService.applyPatch(getJsonNode(INITIAL_STATE), PATCH);

        // THEN
        assertEquals(NEW_STATE, result.toString());
    }
}
