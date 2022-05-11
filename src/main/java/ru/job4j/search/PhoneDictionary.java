package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Return a list of all users that contain the key in any fields.
     * @param key Search key.
     * @return Returns a list of eligible users.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> name = s -> s.getName().contains(key);
        Predicate<Person> surname = s -> s.getSurname().contains(key);
        Predicate<Person> phone = s -> s.getPhone().contains(key);
        Predicate<Person> address = s -> s.getAddress().contains(key);
        Predicate<Person> combine = name.or(surname).or(phone).or(address);
        var result = new ArrayList<Person>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
