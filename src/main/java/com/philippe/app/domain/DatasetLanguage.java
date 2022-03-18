package com.philippe.app.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Not related to this project. Added here as an example of Enum with a constructor and the use of a Map.
 */
public enum DatasetLanguage {
    ENGLISH("ENG", "EN"), WELSH("CYM", "CY");

    private static final Map<String, String> ENUM_MAP = new HashMap<>();
    private static final Map<String, String> REVERSE_ENUM_MAP = new HashMap<>();
    static {
        for (DatasetLanguage s : DatasetLanguage.values()) {
            String code = s.getCode();
            String desc = s.toString();
            ENUM_MAP.put(code, desc);
            REVERSE_ENUM_MAP.put(desc, code);
        }
    }

    private final String code;
    private final String description;
    DatasetLanguage(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return description;
    }

    public static String getDescription(String code) {
        return ENUM_MAP.get(code);
    }

    public static String getCode(String description) {
        return REVERSE_ENUM_MAP.get(description);
    }
}
