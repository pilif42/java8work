package com.philippe.app.service.maths;

import java.util.List;

public interface FormulaService {

  double calculate(int a);

  default double sqrt(int a) {
    return Math.sqrt(a);
  }

  List<Integer> filterList(List<Integer> inputList);
}
