package com.philippe.app.service.strings.impl;

import com.philippe.app.service.strings.SortingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Service
public class SortingServiceImpl implements SortingService {
  @Override public void sortList(List<String> stringList) {
    stringList
        .stream()
        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .sorted()
        .forEach(log::debug);

    // transform a regular object stream to a primitive stream
    Stream.of("a1", "a2", "a3")
        .map(s -> s.substring(1))
        .mapToInt(Integer::parseInt)
        .max()
        .ifPresent(System.out::println);

    // transform a primitive stream to a regular object stream
    IntStream.range(1, 4)
        .mapToObj(i -> "a" + i)
        .forEach(System.out::println);
  }
}
