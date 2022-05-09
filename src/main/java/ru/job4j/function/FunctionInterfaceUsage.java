package ru.job4j.function;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;

public class FunctionInterfaceUsage {
    public static void main(String[] args) {
        Supplier<String> sup = () -> "Supplier";
        System.out.println(sup.get());
        List<String> list = List.of("one", "two", "three", "one", "two", "three");
        Supplier<Set<String>> sups = () -> new HashSet<>(list);
        Set<String> strings = sups.get();
        for (String s : strings) {
            System.out.println(s);
        }
        List<String> list1 = List.of("one", "two", "three", "one", "two", "three");
        Supplier<Set<String>> sup1 = () -> new HashSet<>(list);
        BiConsumer<Integer, String> consumer = (s, s1) -> System.out.println(s + s1);
        Set<String> strings1 = sup1.get();
        int i = 1;
        for (String s : strings1) {
            consumer.accept(i++, " is " + s);
        }
        BiPredicate<String, Integer> cond = (s, d) -> s.contains(d.toString());
        System.out.println("Строка содержит подстроку: " + cond.test("Name123", 123));
        System.out.println("Строка содержит подстроку: " + cond.test("Name", 123));
        Function<String, Character> func = s -> s.charAt(2);
        System.out.println("Третий символ в строке: " + func.apply("first"));
        System.out.println("Третий символ в строке: " + func.apply("second"));
        BiFunction<String, Integer, String> biFunc = (s, f) -> s.concat(" ").concat(f.toString());
        System.out.println("Результат работы бифункции: " + biFunc.apply("Name", 123));
        System.out.println("Результат работы бифункции: " + biFunc.apply("String number", 12345));
        UnaryOperator<StringBuilder> builder = b -> b.reverse();
        System.out.println("Строка после реверса: " + builder.apply(new StringBuilder("String for test")));
        System.out.println("Строка после реверса: " + builder.apply(new StringBuilder("tset rof gnirtS")));
        BinaryOperator<StringBuilder> builder1 = (b1, b2) -> b1.append(" ").append(b2);
        System.out.println(
                "Строка после объединения: " + builder1.apply(
                        new StringBuilder("First string"),
                        new StringBuilder("Second string")
                )
        );
    }
}
