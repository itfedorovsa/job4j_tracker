package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int minus(int z) {
        return z - x;
    }

    public int divide(int p) {
        return p / x;
    }

    public int sumAllOperation(int h) {
       return divide(h) + minus(h) + sum(h) + multiply(h);
    }

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static void main(String[] args) {
        int rslSum = sum(10);
        System.out.println(rslSum);
        int rslMinus = minus(20);
        System.out.println(rslMinus);
        Calculator calculator = new Calculator();
        int rslMultiply = calculator.multiply(5);
        System.out.println(rslMultiply);
        int rslDivide = calculator.divide(15);
        System.out.println(rslDivide);
        int rslSumAll = calculator.sumAllOperation(10);
        System.out.println(rslSumAll);
    }
}
