package com.philippe.app.service.times;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeConversionTest {
    private final TimeConversion timeConversion = new TimeConversion();

    @Test
    public void shouldConvertUTCTimeIntoCSTDuringDayLightSaving() throws ParseException {
        String expectedCST = "2017-06-19T12:41:01.000-05:00";

        String cstTime = timeConversion.convertUtcToCST("2017-06-19:17:41:01.000000");

        assertThat(cstTime, is(expectedCST));
    }

    @Test
    public void shouldConvertUTCTimeIntoCSTAfterDayLightSavingEnds() throws ParseException {
        String expectedCST = "2017-12-19T11:41:01.055-06:00";
        String cstTime = timeConversion.convertUtcToCST("2017-12-19:17:41:01.000055");

        assertThat(cstTime, is(expectedCST));
    }

    @Test
    public void shouldConvertUTCToCSTIfFirst3MillisecondValueIsGreaterThanZero() throws ParseException {
        String expectedCST = "2017-10-19T12:36:01.800-05:00";
        // first 3 milli value - 800
        String cstTime = timeConversion.convertUtcToCST("2017-10-19:17:36:01.800803");

        assertThat(cstTime, is(expectedCST));
    }

    @Test
    public void shouldReturnInputTimeInCaseInputTimeFormatIsNotAsPerExpected() {
        assertThrows(ParseException.class, () -> timeConversion.convertUtcToCST("2017-10-19 17:23.010907"));
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayHappyPathWinterTime() {
        String cstTime = timeConversion.convertBasicIsoDateToCSTBeginningOfDay("20180120");
        assertThat(cstTime, is("2018-01-20T00:00:00-06:00"));
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayHappyPathSummerTime() {
        String cstTime = timeConversion.convertBasicIsoDateToCSTBeginningOfDay("20181001");
        assertThat(cstTime, is("2018-10-01T00:00:00-05:00"));
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidMonth() {
        DateTimeException exception = assertThrows(DateTimeException.class, () -> timeConversion.convertBasicIsoDateToCSTBeginningOfDay("20181320"));
        assertEquals("Text '20181320' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13", exception.getMessage());
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidDay() {
        DateTimeException exception = assertThrows(DateTimeException.class, () -> timeConversion.convertBasicIsoDateToCSTBeginningOfDay("20181140"));
        assertEquals("Text '20181140' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 40", exception.getMessage());
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidValue() {
        final String random = "random";

        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> timeConversion.convertBasicIsoDateToCSTBeginningOfDay(random));
        assertEquals(format("Text '%s' could not be parsed at index 0", random), exception.getMessage());
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidDate() {
        DateTimeException exception = assertThrows(DateTimeException.class, () -> timeConversion.convertBasicIsoDateToCSTBeginningOfDay("20180229")); // This Feb date does not exist in 2018. Max is 28.
        assertEquals("Text '20180229' could not be parsed: Invalid date 'February 29' as '2018' is not a leap year", exception.getMessage());
    }
}