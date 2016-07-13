package com.philippe.app.service.flatmaps.impl;

import com.philippe.app.domain.Bar;
import com.philippe.app.domain.Foo;
import com.philippe.app.service.flatmaps.TestService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Named
@Slf4j
public class TestServiceImpl implements TestService {

  @Override
  public void myExperiments() {
    List<Foo> foos = new ArrayList<>();
    IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

    foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.getBars().add(new Bar("Bar" + i + " <- " + f.getName()))));

    /**
     * Displays:
     *  Bar1 <- Foo1
     *  Bar2 <- Foo1
     *  Bar3 <- Foo1
     *  Bar1 <- Foo2
     *  Bar2 <- Foo2
     *  Bar3 <- Foo2
     *  Bar1 <- Foo3
     *  Bar2 <- Foo3
     *  Bar3 <- Foo3
     *
     *  We do the following because: (from http://www.mkyong.com/java8/java-8-flatmap-example/)
     *  In Java 8, Stream can hold different data types, for examples:
     *      - Stream<String[]>
     *      - Stream<Set<String>>
     *      - Stream<List<String>>
     *      - Stream<List<Object>>
     *  But, the Stream operations (filter, sum, distinct…) and collectors do not support it, so, we need flatMap() to do the following conversion :
     *      - Stream<String[]>		-> flatMap ->	Stream<String>
     *      - Stream<Set<String>>	-> flatMap ->	Stream<String>
     *      - Stream<List<String>>	-> flatMap ->	Stream<String>
     *      -   Stream<List<Object>>	-> flatMap ->	Stream<Object>
     */
    foos.stream().flatMap(f -> f.getBars().stream()).forEach(b -> log.debug(b.getName()));
  }

}
