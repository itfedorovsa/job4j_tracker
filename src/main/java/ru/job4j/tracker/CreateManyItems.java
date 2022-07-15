package ru.job4j.tracker;

public class CreateManyItems implements UserAction {
    private final Output out;

    public CreateManyItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add many Items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Creating process started ===");
        for (int index = 0; index < 1200000; index++) {
            Item item = new Item(index, "VeryVeryVeryLongName");
            tracker.add(item);
            out.println("Added item: " + item);
        }
        return true;
    }
}
