package com.philippe.app.maths;

public interface Formula {

  double calculate(int a);

  default double sqrt(int a) {
    return Math.sqrt(a);
  }
}
