package com.philippe.app.service.futures;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SquareCalculatorTest {
    @Test
    public void toDemo() throws InterruptedException, ExecutionException {
        Future<Integer> future = new SquareCalculator().calculate(10);
        while(!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(300);
        }

        Integer result = future.get();
        System.out.println("result is " + result);

        // get() has an overloaded version that takes a timeout and a TimeUnit as arguments: future.get(500, TimeUnit.MILLISECONDS);
    }
}
