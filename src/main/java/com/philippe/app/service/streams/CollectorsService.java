package com.philippe.app.service.streams;

import com.philippe.app.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CollectorsService {

  public void myExperiments() {
    List<Person> personsList = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
            new Person("David", 12));

    // Next section displays [Peter, Pamela]
    List<Person> filteredList = personsList.stream()
        .filter(p -> p.getName().startsWith("P"))
        .collect(Collectors.toList());
    log.debug("filteredList is {}", filteredList);

// Old way on the next line
//    Comparator<Person> byPersonAgeAscending = (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge());
    Comparator<Person> byPersonAgeAscending = Comparator.comparingInt(Person::getAge);
    List<Person> sortedList = personsList.stream().sorted(byPersonAgeAscending).collect(Collectors.toList());
    log.debug("sorted List = {}", sortedList);

    /**
     * Next section displays
     * age 18: [Max]
     * age 23: [Peter, Pamela]
     * age 12: [David]
     */
    Map<Integer, List<Person>> personsByAge = personsList.stream().sorted(byPersonAgeAscending)
        .collect(Collectors.groupingBy(p -> p.getAge()));
    personsByAge.forEach((age, p) -> log.debug("age {}: {}", age, p));

    Double averageAge = personsList.stream().collect(Collectors.averagingInt(p -> p.getAge()));
    log.debug("averageAge = {}", averageAge);

    // Displays ageSummary = IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
    IntSummaryStatistics ageSummary = personsList.stream().collect(Collectors.summarizingInt(p -> p.getAge()));
    log.debug("ageSummary = {}", ageSummary);

    // Displays phrase = In Germany, Max and Peter and Pamela are of legal age.
    String phrase = personsList.stream().filter(p -> p.getAge() >= 18).map(p -> p.getName())
        .collect(Collectors.joining(" and ", "In Germany, ", " are of legal age."));
    log.debug("phrase = {}", phrase);

    // Displays map = {18=Max, 23=Peter;Pamela, 12=David}
    Map<Integer, String> map = personsList.stream()
        .collect(Collectors.toMap(
            p -> p.getAge(),
            p -> p.getName(),
            (name1, name2) -> name1 + ";" + name2));
    log.debug("map = {}", map);
  }
}
