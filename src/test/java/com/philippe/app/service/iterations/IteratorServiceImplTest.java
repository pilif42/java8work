package com.philippe.app.service.iterations;

import com.philippe.app.service.strings.ManipulationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IteratorServiceImplTest {

    private final static String STRING_1 = "This is the beginning.";
    private final static String STRING_2 = "This is the middle.";
    private final static String STRING_3 = "This is the end.";

    @InjectMocks
    private IteratorServiceImpl iteratorService;

    @Mock
    private ManipulationService manipulationService;

    @Test
    public void testProcess() {
        // GIVEN
        final BlockingQueue<String> queue = new LinkedBlockingDeque<>(3);
        queue.add(STRING_1);
        queue.add(STRING_2);
        queue.add(STRING_3);

        // WHEN
        iteratorService.process(queue);

        // THEN - if you do NOT need to assert the order of calls on manipulationService.
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(manipulationService, times(3)).transform(captor.capture());

        final List<String> paramList = captor.getAllValues();
        assertEquals(3, paramList.size());
        assertTrue(paramList.containsAll(Arrays.asList(STRING_1, STRING_2, STRING_3)));

        // THEN - if you do need to assert the order of calls on manipulationService.
        InOrder inOrder = inOrder(manipulationService);
        inOrder.verify(manipulationService).transform(argThat((arg) -> arg.equals(STRING_1)));
        inOrder.verify(manipulationService).transform(argThat((arg) -> arg.equals(STRING_2)));
        inOrder.verify(manipulationService).transform(argThat((arg) -> arg.equals(STRING_3)));
    }
}
