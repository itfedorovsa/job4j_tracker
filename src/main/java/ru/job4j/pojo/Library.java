package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book chapter1 = new Book("Part 1", 15);
        Book chapter2 = new Book("Part 2", 20);
        Book chapter3 = new Book("Clean Code", 30);
        Book chapter4 = new Book("Part 4", 50);
        Book[] library = new Book[4];
        library[0] = chapter1;
        library[1] = chapter2;
        library[2] = chapter3;
        library[3] = chapter4;
        for (Book parts : library) {
            System.out.println(parts.getName() + " - " + parts.getPages());
        }
        Book temp = library[0];
        library[0] = library[3];
        library[3] = temp;
        for (Book parts : library) {
            System.out.println(parts.getName() + " - " + parts.getPages());
        }
        for (Book parts : library) {
            if ("Clean Code".equals(parts.getName())) {
                System.out.println(parts.getName());
            }
        }
    }
}
