package com.philippe.app.service.strings.impl;

import com.philippe.app.service.strings.SortingService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.List;

@Slf4j
@Named
public class SortingServiceImpl implements SortingService {
  @Override public void sortList(List<String> stringList) {
    stringList
        .stream()
        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .sorted()
        .forEach(log::debug);
  }
}
