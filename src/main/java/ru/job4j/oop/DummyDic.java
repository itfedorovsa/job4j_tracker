package ru.job4j.oop;

public class DummyDic {

    public String engToRus(String eng) {
        String unknown = "Неизвестное слово. " + eng;
        return unknown;
    }

    public static void main(String[] args) {
        DummyDic dict = new DummyDic();
        String word = dict.engToRus("study");
        System.out.println(word);
    }
}
