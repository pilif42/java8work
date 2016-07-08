package com.philippe.app.endpoint;

import com.philippe.app.service.maths.FormulaService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON })
@Slf4j
public class TestEndpoint {

  @Inject
  private FormulaService formulaService;

  @GET
  @Path("/")
  public final Response tester() {
    log.debug("Entering tester ...");
    log.debug("FormulaService of calculate 100 is {}", formulaService.calculate(100));
    log.debug("FormulaService of sqrt 16 is {}", formulaService.sqrt(16));
    return Response.status(Response.Status.OK).build();
  }
}
