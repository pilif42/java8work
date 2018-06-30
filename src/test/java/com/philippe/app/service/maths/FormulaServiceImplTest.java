package com.philippe.app.service.maths;

import com.philippe.app.service.maths.impl.FormulaServiceImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormulaServiceImplTest {
    @Test
    public void filterList() {
        final List<Integer> inputList = Arrays.asList(10, 13, 18);
        final List<Integer> filteredList = new FormulaServiceImpl().filterList(inputList);

        assertEquals(2, filteredList.size());
        assertTrue(filteredList.containsAll(Arrays.asList(10, 13)));
    }
}
