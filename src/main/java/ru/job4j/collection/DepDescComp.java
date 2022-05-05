package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String first = "";
        String second = "";
        for (String el : o1.split("/")) {
            first = el;
            break;
        }
        for (String el : o2.split("/")) {
            second = el;
            break;
        }
        int rsl = second.compareTo(first);
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }
}
