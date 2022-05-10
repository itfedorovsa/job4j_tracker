package ru.job4j.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = (i, s) -> map.put(i, s);
            biCon.accept(1, "one");
            biCon.accept(2, "two");
            biCon.accept(3, "three");
            biCon.accept(4, "four");
            biCon.accept(5, "five");
            biCon.accept(6, "six");
            biCon.accept(7, "seven");

        BiPredicate<Integer, String> biPred = (i, g) -> i % 2 == 0 || g.length() == 4;
            for (Integer index : map.keySet()) {
                if (biPred.test(index, map.get(index))) {
                    System.out.println("key: " + index + " value: " + map.get(index));
                }
            }

        Supplier<List<String>> sup = () -> new ArrayList<>(map.values());
        Consumer<String> con = v -> System.out.println(v);
        Function<String, String> func = inStr -> inStr.toUpperCase();
            for (String s : sup.get()) {
                con.accept(func.apply(s));
            }
    }
}


