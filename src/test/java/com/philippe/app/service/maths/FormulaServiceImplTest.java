package com.philippe.app.service.maths;

import com.philippe.app.service.maths.impl.FormulaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
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
}
