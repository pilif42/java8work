package com.philippe.app.endpoint;

import com.philippe.app.service.dates.CalendarService;
import com.philippe.app.service.maths.FormulaService;
import com.philippe.app.service.strings.SortingService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON })
@Slf4j
public class TestEndpoint {

  @Inject
  private CalendarService calendarService;

  @Inject
  private FormulaService formulaService;

  @Inject
  private SortingService sortingService;

  @GET
  @Path("/")
  public final Response tester() {
    log.debug("Entering tester ...");

    log.debug("FormulaService of calculate 100 is {}", formulaService.calculate(100));
    log.debug("FormulaService of sqrt 16 is {}", formulaService.sqrt(16));

    List<String> timeZoneIds = calendarService.provideAvailableTimeZoneIds();
    timeZoneIds.forEach(log::debug);


    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
    sortingService.sortList(myList);

    return Response.status(Response.Status.OK).build();
  }
}
