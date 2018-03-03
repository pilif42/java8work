package com.philippe.app.service.times;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.TimeZone.getTimeZone;

public class TimeConversion {

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd:HH:mm:ss.SSSSSS";

    private static final String DATE_TIME_FORMAT_WITH_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static final ZoneId CST_ZONE_ID = ZoneId.of("America/Chicago");

    /**
     * Convert UTC timestamp to CST
     * e.g. 2017-10-19:17:41:01.000055  -> 2017-10-19T11:41:01.055-05:00
     *
     * @param utcDateTime Input DateTime in UTC format
     * @return DateTime string in CST format
     */
    public String convertUtcToCST(String utcDateTime) throws ParseException {
        Date cstDateTime = getDateObjectFromDateTimeString(utcDateTime);
        OffsetDateTime offsetDateTime = cstDateTime.toInstant().atZone(CST_ZONE_ID).toOffsetDateTime();
        return getTimeStringContainingOffset(offsetDateTime);
    }

    /**
     * Convert a basicIsoDate to an ISO format with a beginning of day time referencing CST
     * e.g. 20180120 -> 2018-01-20T00:00:00-06:00
     *      20181001 -> 2018-10-01T00:00:00-05:00
     *
     * @param basicIsoDate in the format yyyyMMdd
     * @return an ISO format with a beginning of day time referencing CST
     */
    public String convertBasicIsoDateToCSTBeginningOfDay(String basicIsoDate) {
        return LocalDate.parse(basicIsoDate, BASIC_ISO_DATE).atStartOfDay(CST_ZONE_ID)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private String getTimeStringContainingOffset(TemporalAccessor offsetDateTime) {
        return ofPattern(DATE_TIME_FORMAT_WITH_OFFSET).format(offsetDateTime);
    }

    private Date getDateObjectFromDateTimeString(String utcDateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        sdf.setTimeZone(getTimeZone("UTC"));
        return sdf.parse(convertDateTime(utcDateTime));
    }

    /**
     * if first 3 part of the SSSSSS in the input datetime is greater than 0,
     * then ignore the last 3 number
     * e.g. 801987 -> 000801
     * 000345 -> 000345
     *
     * @param dateTime -> input string to convert.
     * @return Datetime where milliseconds is converted if required
     */
    private String convertDateTime(String dateTime) {
        String milliseconds = dateTime.substring(dateTime.lastIndexOf(".") + 1);
        final int mid = milliseconds.length() / 2; //get the middle of the String
        String firstHalfMilliString = milliseconds.substring(0, mid);
        int firstHalfMillisecond = parseInt(firstHalfMilliString);
        if (firstHalfMillisecond > 0) {
            return dateTime.substring(0, dateTime.lastIndexOf(".") + 1) + "000" + firstHalfMilliString;
        } else {
            return dateTime;
        }
    }
}
