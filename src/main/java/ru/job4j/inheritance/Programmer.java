package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private int computerScience;

    public Programmer(String name, String surname, String profession, String education, String project, int computerScience) {
        super(name, surname, profession, education, project);
        this.computerScience = computerScience;
    }

    public Programmer() {
    }

    public int computerScienceLvl() {
        return computerScience;
    }
}
