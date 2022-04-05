package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private String dentistry;

    public Dentist(String name, String surname, String profession, String education, String healing, String dentistry) {
        super(name, surname, profession, education, healing);
        this.dentistry = dentistry;
    }

    public Dentist() {
    }
}
