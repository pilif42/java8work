package com.philippe.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    log.debug("Entrance of app...");
    SpringApplication.run(Application.class, args);
  }
}
