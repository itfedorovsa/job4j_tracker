package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> names = new ArrayList<>();
        for (Item ind : items) {
            if (key.equals(ind.getName())) {
                names.add(ind);
            }
        }
        return names;
    }

    public Item findById(int id) {
        int ind = indexOf(id);
        return ind != -1 ? items.get(ind) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
         return rsl;
        }

    public boolean replace(int id, Item item) {
        int ind = indexOf(id);
        if (ind != -1) {
            item.setId(id);
            items.set(ind, item);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        int ind = indexOf(id);
        if (ind != -1) {
            items.remove(ind);
            return true;
        }
        return false;
    }
}