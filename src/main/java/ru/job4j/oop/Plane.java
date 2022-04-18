package ru.job4j.oop;

public class Plane implements Vehicle {
    private final String nameClass = getClass().getSimpleName();

    @Override
    public void move() {
        System.out.println(nameClass + " летает по воздуху");
    }

    @Override
    public void fuel() {
        System.out.println(nameClass + " использует авиационное топливо");
    }
}
