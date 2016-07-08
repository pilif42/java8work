package com.philippe.app.service.maths.impl;

import com.philippe.app.service.maths.FormulaService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class FormulaServiceImpl implements FormulaService {

  @Override
  public double calculate(int a) {
    log.debug("Entrance of calculate with a = {}", a);
    return sqrt(a * 100);
  }

}
