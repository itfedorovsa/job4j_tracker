package ru.job4j.inheritance;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {
    private static final String EXTRA_TOMATO = " + Extra Tomato";

    @Override
    public String name() {
        return super.name() + EXTRA_TOMATO;
    }
}
