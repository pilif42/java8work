package com.philippe.app.service.dates;

import com.philippe.app.service.dates.impl.CalendarServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.DateTimeException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private CalendarServiceImpl calendarService;

    @Test
    public void convertDateFormatToIsoLocalDateHappyPath() {
        assertEquals("2099-10-01", calendarService.convertDateFormatToIsoLocalDate("991001"));
    }

    @Test
    public void convertConcourseDateFormatToIsoLocalDateErrorPathInvalidMonth() {
        expectedException.expect(DateTimeException.class);
        expectedException.expectMessage("Invalid value for MonthOfYear (valid values 1 - 12): 13");

        calendarService.convertDateFormatToIsoLocalDate("181301");
    }
}
