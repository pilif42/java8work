package com.philippe.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class CustomObjectMapper extends ObjectMapper {

    public static final String DATE_FORMAT_IN_JSON = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * Custom Object Mapper Constructor
     */
    public CustomObjectMapper() {
        this.setDateFormat(new SimpleDateFormat(DATE_FORMAT_IN_JSON));
    }
}