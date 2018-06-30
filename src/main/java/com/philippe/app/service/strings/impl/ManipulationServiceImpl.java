package com.philippe.app.service.strings.impl;

import com.philippe.app.service.strings.ManipulationService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ManipulationServiceImpl implements ManipulationService {

    private static final String EQUAL_SEPARATOR = "=";
    private static final String HTTPS_PORT = "443";
    private static final String PIPE_SEPARATOR = "\\|";

    private static final Predicate<String> validHttpsUrl = url -> url.endsWith(HTTPS_PORT);

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

    @Override
    public Optional<String> filterUrls(List<String> urlList) {
        Optional<String> result = Optional.empty();
        if (!CollectionUtils.isEmpty(urlList)) {
            result = urlList.stream().filter(validHttpsUrl).findFirst();
        }
        return result;
    }
}
