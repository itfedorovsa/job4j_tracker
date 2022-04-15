package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("The method describes the \"drive\" action");
    }

    @Override
    public void passengers(int quantity) {
        System.out.println("The method describes the quantity of passengers");
    }

    @Override
    public float gasTotalCost(float litres) {
        float gallonPrice = 0.0f;
        return gallonPrice * litres;
    }
}
