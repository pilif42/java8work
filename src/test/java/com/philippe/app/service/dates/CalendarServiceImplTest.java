package com.philippe.app.service.dates;

import com.philippe.app.service.dates.impl.CalendarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class CalendarServiceImplTest {

    @InjectMocks
    private CalendarServiceImpl calendarService;

    @Test
    public void convertDateFormatToIsoLocalDateHappyPath() {
        assertEquals("2099-10-01", calendarService.convertDateFormatToIsoLocalDate("991001"));
    }

    @Test
    public void convertConcourseDateFormatToIsoLocalDateErrorPathInvalidMonth() {
        Exception exception = assertThrows(DateTimeException.class, () -> calendarService.convertDateFormatToIsoLocalDate("181301"));
        assertEquals("Text '181301' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13", exception.getMessage());
    }

    @Test
    public void printNow_expectNowInISO_DATE_TIME() {
        LocalDateTime now = LocalDateTime.now();
        try (MockedStatic<LocalDateTime> localDateTimeMock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            localDateTimeMock.when(LocalDateTime::now).thenReturn(now);
            assertEquals(now.format(DateTimeFormatter.ISO_DATE_TIME), calendarService.printNow());
        }
    }
}
