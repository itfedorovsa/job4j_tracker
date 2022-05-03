package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl;
        int count = Integer.compare(left.length(), right.length());
        int length = Math.min(left.length(), right.length());
        for (int index = 0; index < length; index++) {
            rsl = Character.compare(left.charAt(index), right.charAt(index));
            if (rsl != 0) {
                return rsl;
            }
        }
        return count;
    }
}
