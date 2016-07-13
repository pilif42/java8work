package com.philippe.app.domain;

import lombok.Data;

@Data
public class Bar {
  String name;

  public Bar(String name) {
    this.name = name;
  }
}
