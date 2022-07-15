package ru.job4j.tracker;

public class DeleteManyItems implements UserAction {
    private final Output out;

    public DeleteManyItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete many Items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("=== Delete many Items ===");
        for (int index = 0; index < 1100000; index++) {
            tracker.delete(index);
            out.println("Item was successfully deleted.");
        }
        return true;
    }
}
