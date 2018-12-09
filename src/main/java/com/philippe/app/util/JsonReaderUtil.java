package com.philippe.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.PathNotFoundException;
import com.philippe.app.exception.CustomException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * JsonReaderUtil provides utility methods to get jsonElements for a given JsonPath.
 *
 * We have been using this utility with a DocumentContext built using GsonJsonProvider as per below:
 * public static DocumentContext getDocumentContext(String filePath) throws IOException {
 *     List<String> events = Files.readAllLines(Paths.get(filePath).toAbsolutePath());
 *     assertEquals(1, events.size());
 *
 *     final Configuration gson = Configuration.builder().jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider()).build();
 *     return JsonPath.using(gson).parse(events.get(0));
 * }
 *
 * Notice the gson. Very important as at read time, behaviour varies depending on this configuration. Other possible configs are shown at https://github.com/json-path/JsonPath/blob/master/json-path/src/test/java/com/jayway/jsonpath/ProviderInTest.java
 *
 */
public class JsonReaderUtil {

    public static final String CANNOT_LOCATE_JSONPATH_ERROR_MESSAGE = "Cannot locate jsonPath \"%s\" from message \n %s";

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonReaderUtil.class);

    private JsonReaderUtil() {
    }

    public static String getStringValueFromJson(DocumentContext json, String jPath) {
        try {
            return json.read(jPath, String.class);
        } catch (PathNotFoundException | ClassCastException e) {
            final String errorMessage = String.format(CANNOT_LOCATE_JSONPATH_ERROR_MESSAGE, jPath, json);
            LOGGER.info(errorMessage, jPath, json);
            LOGGER.trace(e.getMessage(), e);
            return null;
        }
    }

    public static boolean getCriticalBooleanValueFromJson(DocumentContext json, String jPath) throws CustomException {
        try {
            return json.read(jPath, Boolean.class);
        } catch (PathNotFoundException | ClassCastException e) {
            throw getValidationException(json, jPath, e);
        }
    }

    public static String getCriticalStringValueFromJson(DocumentContext json, String jPath) throws CustomException {
        try {
            final String value = json.read(jPath, String.class);
            if (isBlank(value)) {
                throw getValidationException(json, jPath, null);
            }
            return value;
        } catch (PathNotFoundException | ClassCastException e) {
            throw getValidationException(json, jPath, e);
        }
    }

    public static JsonObject getCriticalObjectFromJson(DocumentContext json, String jPath) throws CustomException {
        try {
            return json.read(jPath, JsonObject.class);
        } catch (PathNotFoundException | ClassCastException e) {
            throw getValidationException(json, jPath, e);
        }
    }

    public static List<String> getStringValuesFromJson(DocumentContext json, String jPath) {
        List<String> values = new ArrayList<>();
        try {
            JsonElement element = json.read(jPath);
            if (element.isJsonArray()) {

                List<String> valuesFromArray = getStringValuesFromJsonArray((JsonArray) element);
                if (!valuesFromArray.isEmpty()) {
                    values.addAll(valuesFromArray);
                }
            } else if (element.isJsonNull()) {
                String errorMessage = String.format("Null json element for jsonPath %s in message %s", jPath, json);
                LOGGER.trace(errorMessage);
            } else {
                String value = element.getAsString();
                if (StringUtils.isNotBlank(value)) {
                    values.add(value);
                }
            }
        } catch (PathNotFoundException | ClassCastException e) {
            final String errorMessageGettingStringValueFromJson = String.format(CANNOT_LOCATE_JSONPATH_ERROR_MESSAGE, jPath, json);
            LOGGER.info(errorMessageGettingStringValueFromJson, jPath, json);
            LOGGER.debug(e.getMessage(), e);
        }
        return values;
    }

    public static DocumentContext delete(DocumentContext jsonEventObject, String jsonPath) {
        return jsonEventObject.delete(jsonPath);
    }

    public static void beautifyJsonString(String jsonString) throws Exception {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonString, Object.class);
        System.out.println("Beautiful json is: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
    }

    private static List<String> getStringValuesFromJsonArray(JsonArray jsonArray) {
        List<String> values = new ArrayList<>();

        if (Objects.nonNull(jsonArray)) {
            for (JsonElement arrayElement : jsonArray) {
                String value = arrayElement.getAsString();
                if (!isBlank(value)) {
                    values.add(value);
                }
            }
        }

        return values;
    }

    private static CustomException getValidationException(DocumentContext json, String jPath, RuntimeException e) {
        final String errorMessage = String.format(CANNOT_LOCATE_JSONPATH_ERROR_MESSAGE, jPath, json);
        LOGGER.info(errorMessage, jPath, json);
        return new CustomException(CustomException.Fault.VALIDATION_FAILED, errorMessage, e);
    }
}