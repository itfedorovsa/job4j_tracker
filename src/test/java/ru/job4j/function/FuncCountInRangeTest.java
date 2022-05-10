package ru.job4j.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FuncCountInRangeTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        FuncCountInRange funcRange = new FuncCountInRange();
        List<Double> result = funcRange.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        FuncCountInRange funcRange = new FuncCountInRange();
        List<Double> result = funcRange.diapason(2, 5, x -> 2 * x * x + 3 * x + 1);
        List<Double> expected = Arrays.asList(15D, 28D, 45D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        FuncCountInRange funcRange = new FuncCountInRange();
        List<Double> result = funcRange.diapason(1, 4, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(3D, 9D, 27D);
        assertThat(result, is(expected));
    }

}