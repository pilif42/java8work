package com.philippe.app.service.dates.impl;

import com.philippe.app.service.dates.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {
  @Override
  public List<String> provideAvailableTimeZoneIds() {
    log.debug("Entrance of provideAvailableTimeZoneIds...");
    String[] avalableIds = TimeZone.getAvailableIDs();
    return Arrays.stream(avalableIds).collect(Collectors.toList());
  }
}
