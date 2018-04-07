package com.philippe.app.service.strings.impl;

import com.philippe.app.service.strings.ManipulationService;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ManipulationServiceImpl implements ManipulationService {

    private static final String EQUAL_SEPARATOR = "=";
    private static final String PIPE_SEPARATOR = "\\|";

    @Override
    public Map<String, String> process(String aString) {
        Map<String, String> map = null;

        if (!StringUtils.isEmpty(aString)) {
            map = Arrays.stream(aString.split(PIPE_SEPARATOR, -1))
                    .filter(filetype -> filetype.contains(EQUAL_SEPARATOR))
                    .collect(Collectors.toMap(s -> s.split(EQUAL_SEPARATOR)[0], s -> s.split(EQUAL_SEPARATOR)[1]));
        }

        return map;
    }
}
