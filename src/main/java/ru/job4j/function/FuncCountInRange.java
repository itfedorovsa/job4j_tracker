package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FuncCountInRange {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (double index = start; index < end; index++) {
            list.add(func.apply(index));
        }
        return list;
    }
}
