package com.philippe.app.service.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.IOException;

public interface JsonService {
    /**
     * To provide differences between 2 JSON Strings initialState and newState.
     */
    JsonNode provideDifferences(final String initialState, final String newState) throws IOException;

    /**
     * To apply the patch onto the initial JSON edenJsonNode.
     */
    JsonNode applyPatch(final JsonNode edenJsonNode, final String patch) throws IOException, JsonPatchException;
}
