package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int index = 0; index < value.length; index++) {
            if (value[index].equals(key)) {
                rsl =  index;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element not found");
        }
        return rsl;
    }

    public static boolean sent(String values, String[] abuses) throws ElementAbuseException {
        return true;
    }

    public static void process(String[] value, String key, String[] abuses) {
        try {
            if (indexOf(value, key) != -1) {
                sent(key, abuses);
            }
        } catch (ElementAbuseException ea) {
            ea.printStackTrace();
        } catch (ElementNotFoundException en) {
            en.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] array = {"1", "2", "3"};
        try {
            indexOf(array, "5");
        } catch (ElementNotFoundException a) {
            a.printStackTrace();
        }
    }
}
