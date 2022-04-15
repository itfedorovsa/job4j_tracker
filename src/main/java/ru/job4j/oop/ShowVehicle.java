package ru.job4j.oop;

public class ShowVehicle {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle airbus = new Plane();
        Vehicle train = new Train();
        Vehicle locomotive = new Train();
        Vehicle bus = new Bus();
        Vehicle[] vehicles = new Vehicle[] {plane, airbus, train, locomotive, bus};
        for (Vehicle index : vehicles) {
            index.move();
            index.fuel();
        }
    }
}
