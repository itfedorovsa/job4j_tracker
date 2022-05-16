package ru.job4j.inheritance;

public class PizzaExtraCheese extends Pizza {
    private static final String EXTRA_CHEESE = " + Extra Cheese";

    @Override
    public String name() {
        return super.name() + EXTRA_CHEESE;
    }
}
