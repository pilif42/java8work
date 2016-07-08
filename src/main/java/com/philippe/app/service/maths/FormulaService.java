package com.philippe.app.service.maths;

public interface FormulaService {

  double calculate(int a);

  default double sqrt(int a) {
    return Math.sqrt(a);
  }
}
