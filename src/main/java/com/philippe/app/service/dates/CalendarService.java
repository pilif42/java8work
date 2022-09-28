package com.philippe.app.service.dates;

import java.util.List;

public interface CalendarService {
  List<String> provideAvailableTimeZoneIds();

  /**
   * Convert Date to ISO_LOCAL_DATE
   * e.g. 181001 -> 2018-10-01
   *
   * @param date in format yyMMdd
   * @return date string in ISO_LOCAL_DATE format
   */
  String convertDateFormatToIsoLocalDate(String date);

  String printNow();
}
