package com.philippe.app.service.maths;

import com.philippe.app.service.maths.impl.FormulaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FormulaServiceImplTest {

    @InjectMocks
    private FormulaServiceImpl formulaService;

    @Test
    public void filterList() {
        final List<Integer> inputList = Arrays.asList(10, 13, 18);
        final List<Integer> filteredList = formulaService.filterList(inputList);

        assertEquals(2, filteredList.size());
        assertTrue(filteredList.containsAll(Arrays.asList(10, 13)));
    }

    @Test
    public void fibonacciOf() {
        assertEquals(0, formulaService.fibonacciOf(0));
        assertEquals(1, formulaService.fibonacciOf(1));
        assertEquals(1, formulaService.fibonacciOf(2));
        assertEquals(2, formulaService.fibonacciOf(3));
        assertEquals(317811, formulaService.fibonacciOf(28));
    }
}
