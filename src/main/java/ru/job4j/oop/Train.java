package ru.job4j.oop;

public class Train implements Vehicle {
    String nameClass = getClass().getSimpleName();

    @Override
    public void move() {
        System.out.println(nameClass + " передвигается по железной дороге");
    }

    @Override
    public void fuel() {
        System.out.println(nameClass + " использует дизельное топливо");
    }
}
