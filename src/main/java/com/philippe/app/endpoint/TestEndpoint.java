package com.philippe.app.endpoint;

import com.philippe.app.service.dates.CalendarService;
import com.philippe.app.service.flatmaps.TestService;
import com.philippe.app.service.maths.FormulaService;
import com.philippe.app.service.persons.CollectorsService;
import com.philippe.app.service.strings.SortingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/tester", produces = "application/json")
@Slf4j
public class TestEndpoint {

  @Autowired
  private CalendarService calendarService;

  @Autowired
  private CollectorsService collectorsService;

  @Autowired
  private FormulaService formulaService;

  @Autowired
  private SortingService sortingService;

  @Autowired
  private TestService testService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public final ResponseEntity theGet() {
    log.debug("Entering tester ...");

    log.debug("FormulaService of calculate 100 is {}", formulaService.calculate(100));
    log.debug("FormulaService of sqrt 16 is {}", formulaService.sqrt(16));

    List<String> timeZoneIds = calendarService.provideAvailableTimeZoneIds();
    timeZoneIds.forEach(log::debug);


    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
    sortingService.sortList(myList);

    collectorsService.myExperiments();

    testService.myExperiments();

    return ResponseEntity.ok().build();
  }
}
