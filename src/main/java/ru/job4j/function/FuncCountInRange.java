package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FuncCountInRange {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        Supplier<List<Double>> lst = ArrayList::new;
        List<Double> list = lst.get();
        Consumer<Double> addValue = list::add;
        for (double index = start; index < end; index++) {
            addValue.accept(func.apply(index));
        }
        return list;
    }

    /*public double linear(int start, int end, Function<Double, Double> func) {
        Function<Double, Double> function = s -> s.2 * x + 1;
    }

    public double quadratic(int start, int end, Function<Double, Double> func) {
    }

    public double exponential(int start, int end, Function<Double, Double> func) {
        return Math.pow(start, 3);
    }*/
}
