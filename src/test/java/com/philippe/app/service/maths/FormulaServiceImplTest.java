package com.philippe.app.service.maths;

import com.philippe.app.service.maths.impl.FormulaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,1", "3,2", "28,317811"})
    public void fibonacciOf(int input, int expectedOutput) {
        assertEquals(expectedOutput, formulaService.fibonacciOf(input));
    }
}
