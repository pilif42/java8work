package com.philippe.app.service.persons.impl;

import com.philippe.app.domain.Person;
import com.philippe.app.service.persons.CollectorsService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@Slf4j
public class CollectorsServiceImpl implements CollectorsService {

  @Override public void myExperiments() {
    List<Person> personsList = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
            new Person("David", 12));

    // Next section displays [Peter, Pamela]
    List<Person> filteredList = personsList.stream()
        .filter(p -> p.getName().startsWith("P"))
        .collect(Collectors.toList());
    log.debug("filteredList is {}", filteredList);

    /**
     * Next section displays
     * age 18: [Max]
     * age 23: [Peter, Pamela]
     * age 12: [David]
     */
    Map<Integer, List<Person>> personsByAge = personsList.stream()
        .collect(Collectors.groupingBy(p -> p.getAge()));
    personsByAge.forEach((age, p) -> log.debug("age {}: {}", age, p));

    Double averageAge = personsList.stream().collect(Collectors.averagingInt(p -> p.getAge()));
    log.debug("averageAge = {}", averageAge);

    // Displays ageSummary = IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
    IntSummaryStatistics ageSummary = personsList.stream().collect(Collectors.summarizingInt(p -> p.getAge()));
    log.debug("ageSummary = {}", ageSummary);
  }
}
