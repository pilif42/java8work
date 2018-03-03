package com.philippe.app.service.times;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TimeConversionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldConvertUTCTimeIntoCSTDuringDayLightSaving() throws ParseException {
        String expectedCST = "2017-06-19T12:41:01.000-05:00";
        TimeConversion timeConversion = new TimeConversion();
        String cstTime = timeConversion.convertUtcToCST("2017-06-19:17:41:01.000000");

        assertThat(cstTime, is(expectedCST));
    }

    @Test
    public void shouldConvertUTCTimeIntoCSTAfterDayLightSavingEnds() throws ParseException {
        String expectedCST = "2017-12-19T11:41:01.055-06:00";
        TimeConversion timeConversion = new TimeConversion();
        String cstTime = timeConversion.convertUtcToCST("2017-12-19:17:41:01.000055");

        assertThat(cstTime, is(expectedCST));
    }

    @Test
    public void shouldConvertUTCToCSTIfFirst3MillisecondValueIsGreaterThanZero() throws ParseException {
        String expectedCST = "2017-10-19T12:36:01.800-05:00";
        TimeConversion timeConversion = new TimeConversion();
        // first 3 milli value - 800
        String cstTime = timeConversion.convertUtcToCST("2017-10-19:17:36:01.800803");

        assertThat(cstTime, is(expectedCST));
    }

    @Test(expected = ParseException.class)
    public void shouldReturnInputTimeInCaseInputTimeFormatIsNotAsPerExpected() throws ParseException {
        TimeConversion timeConversion = new TimeConversion();
        timeConversion.convertUtcToCST("2017-10-19 17:23.010907");
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayHappyPathWinterTime() {
        String cstTime = new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay("20180120");
        assertThat(cstTime, is("2018-01-20T00:00:00-06:00"));
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayHappyPathSummerTime() {
        String cstTime = new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay("20181001");
        assertThat(cstTime, is("2018-10-01T00:00:00-05:00"));
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidMonth() {
        expectedException.expect(DateTimeException.class);
        expectedException.expectMessage("Invalid value for MonthOfYear (valid values 1 - 12): 13");

        new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay("20181320");
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidDay() {
        expectedException.expect(DateTimeException.class);
        expectedException.expectMessage("Invalid value for DayOfMonth (valid values 1 - 28/31): 40");

        new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay("20181140");
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidValue() {
        final String random = "random";

        expectedException.expect(DateTimeParseException.class);
        expectedException.expectMessage(format("Text '%s' could not be parsed at index 0", random));

        new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay(random);
    }

    @Test
    public void convertBasicIsoDateToCSTBeginningOfDayErrorPathInvalidDate() {
        expectedException.expect(DateTimeException.class);
        expectedException.expectMessage("Invalid date 'February 29' as '2018' is not a leap year");

        new TimeConversion().convertBasicIsoDateToCSTBeginningOfDay("20180229");    // This Feb date does not exist in 2018. Max is 28.
    }
}