package ru.job4j.poly;

public interface Transport {

    void drive();

    void passengers(int quantity);

    float gasTotalCost(float litres);
}
