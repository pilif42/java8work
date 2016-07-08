package com.philippe.app;

import com.philippe.app.endpoint.TestEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Named;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    log.debug("Entrance of app...");
    SpringApplication.run(Application.class, args);
  }

  /**
   * To register classes in the JAX-RS world.
   */
  @Named
  public static class JerseyConfig extends ResourceConfig {
    /**
     * Its public constructor.
     */
    public JerseyConfig() {
      log.debug("entering the JerseyConfig constructor...");
      packages("com.philippe.app");

      register(TestEndpoint.class);
    }
  }
}
