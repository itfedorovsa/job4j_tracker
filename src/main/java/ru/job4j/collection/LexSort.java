package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] left1 = left.split("\\. ");
        String[] right1 = right.split("\\. ");
        int first = Integer.parseInt(left1[0]);
        int second = Integer.parseInt(right1[0]);
        return Integer.compare(first, second);
    }
}
