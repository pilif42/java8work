package com.philippe.app.service.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.diff.JsonDiff;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonServiceImpl implements JsonService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public JsonNode provideDifferences(String initialState, String newState) throws IOException {
        final JsonNode initialJsonNode = getJsonNode(initialState);
        final JsonNode newJsonNode = getJsonNode(newState);
        return JsonDiff.asJson(initialJsonNode, newJsonNode);
    }

    @Override
    public JsonNode applyPatch(JsonNode edenJsonNode, String patch) throws IOException, JsonPatchException {
        final JsonPatch jsonPatch = getJsonPatch(patch);
        return jsonPatch.apply(edenJsonNode);
    }

    public static JsonNode getJsonNode(final String aString) throws IOException {
        return MAPPER.readTree(aString);
    }

    private static JsonPatch getJsonPatch(final String aString) throws IOException {
        return MAPPER.readValue(aString, JsonPatch.class);
    }
}
