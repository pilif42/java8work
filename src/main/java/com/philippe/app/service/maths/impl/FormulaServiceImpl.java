package com.philippe.app.service.maths.impl;

import com.philippe.app.service.maths.FormulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class FormulaServiceImpl implements FormulaService {

  private static final Predicate<Integer> lesserThan = i -> (i < 18);

  @Override
  public double calculate(int a) {
    log.debug("Entrance of calculate with a = {}...", a);
    return sqrt(a * 100);
  }

  @Override
  public List<Integer> filterList(List<Integer> inputList) {
    List<Integer> filteredList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(inputList)) {
      filteredList = inputList.stream().filter(lesserThan).collect(toList());;
    }
    return filteredList;
  }
}
