package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private String surgery;

    public Surgeon(String name, String surname, String profession, String education, String healing, String surgery) {
        super(name, surname, profession, education, healing);
        this.surgery = surgery;
    }

    public Surgeon() {
    }
}
