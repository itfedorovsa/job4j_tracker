package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String line : origin) {
            check.add(line);
        }
        for (String line : text) {
            if (!check.contains(line)) {
                return false;
            }
        }
        return true;
    }
}
