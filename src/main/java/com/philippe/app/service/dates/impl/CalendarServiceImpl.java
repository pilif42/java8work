package com.philippe.app.service.dates.impl;

import com.philippe.app.service.dates.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

  private static final String DATE_FORMAT = "yyMMdd";

  @Override
  public List<String> provideAvailableTimeZoneIds() {
    log.debug("Entrance of provideAvailableTimeZoneIds...");
    String[] avalableIds = TimeZone.getAvailableIDs();
    return Arrays.stream(avalableIds).collect(Collectors.toList());
  }

  @Override
  public String convertDateFormatToIsoLocalDate(String date) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    return LocalDate.parse(date, formatter).format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

  @Override
  public String printNow() {
    return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
  }
}
