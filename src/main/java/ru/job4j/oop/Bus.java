package ru.job4j.oop;

public class Bus implements Vehicle {
    private final String nameClass = getClass().getSimpleName();

    @Override
    public void move() {
        System.out.println(nameClass + " передвигается по дорогам");
    }

    @Override
    public void fuel() {
        System.out.println(nameClass + " использует бензин");
    }
}
