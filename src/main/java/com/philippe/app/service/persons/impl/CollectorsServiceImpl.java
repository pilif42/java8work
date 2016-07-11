package com.philippe.app.service.persons.impl;

import com.philippe.app.domain.Person;
import com.philippe.app.service.persons.CollectorsService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Slf4j
public class CollectorsServiceImpl implements CollectorsService {

  @Override public void myExperiments() {
    List<Person> personsList = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
            new Person("David", 12));

    List<Person> filteredList = personsList.stream()
        .filter(p -> p.getName().startsWith("P"))
        .collect(Collectors.toList());
    log.debug("filteredList is {}", filteredList);
  }
}
