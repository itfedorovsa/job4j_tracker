package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String first = o1.split("/")[0];
        String second =  o2.split("/")[0];
        int rsl = second.compareTo(first);
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }
}
