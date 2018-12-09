package com.philippe.app.endpoint;

import com.philippe.app.domain.User;
import com.philippe.app.representation.UserDTO;
import com.philippe.app.exception.CustomException;
import com.philippe.app.representation.CreatedUserDTO;
import com.philippe.app.service.dates.CalendarService;
import com.philippe.app.service.flatmaps.TestService;
import com.philippe.app.service.kafka.Publisher;
import com.philippe.app.service.maths.FormulaService;
import com.philippe.app.service.persons.CollectorsService;
import com.philippe.app.service.strings.SortingService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

  @Qualifier("beanMapper")
  @Autowired
  private MapperFacade mapperFacade;

  @Autowired
  private Publisher publisher;

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

  @RequestMapping(value = "/{userId}/users", method = RequestMethod.POST)
  public final ResponseEntity<CreatedUserDTO> createUser(@PathVariable("userId") final UUID userId,
                                                         @RequestBody @Valid final UserDTO userDTO,
                                                         BindingResult bindingResult) throws CustomException {
    log.debug("Entering createUser with userId {} and requestObject {}", userId, userDTO);

    if (bindingResult.hasErrors()) {
      throw new CustomException(CustomException.Fault.VALIDATION_FAILED, "Binding errors for user creation: " + bindingResult);
    }

    final User user = mapperFacade.map(userDTO, User.class);

    final CreatedUserDTO response = new CreatedUserDTO();
    response.setId(userId);
    response.setCreated(publisher.send(user));

    String newResourceUrl = ServletUriComponentsBuilder
            .fromCurrentRequest().buildAndExpand(response.getId()).toUri().toString();
    return ResponseEntity.created(URI.create(newResourceUrl)).body(response);
  }
}
