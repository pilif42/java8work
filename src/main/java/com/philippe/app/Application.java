package com.philippe.app;

import com.philippe.app.maths.Formula;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    log.debug("Entrance of app...");
    ApplicationContext ctx = SpringApplication.run(Application.class, args);

    Formula formula = new Formula() {
      @Override
      public double calculate(int a) {
        return sqrt(a * 100);
      }
    };
    log.debug("Formula of calculate 100 is {}", formula.calculate(100));
    log.debug("Formula of sqrt 16 is {}", formula.sqrt(16));
  }
}
