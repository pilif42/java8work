package com.philippe.app.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Foo {
  String name;
  List<Bar> bars = new ArrayList<>();

  public Foo(String name) {
    this.name = name;
  }
}
