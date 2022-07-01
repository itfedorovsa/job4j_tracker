package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    @Override
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(items);
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> names = new ArrayList<>();
        for (Item ind : items) {
            if (key.equals(ind.getName())) {
                names.add(ind);
            }
        }
        return names;
    }

    @Override
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

    @Override
    public boolean replace(int id, Item item) {
        int ind = indexOf(id);
        if (ind != -1) {
            item.setId(id);
            items.set(ind, item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int ind = indexOf(id);
        if (ind != -1) {
            items.remove(ind);
            return true;
        }
        return false;
    }
}