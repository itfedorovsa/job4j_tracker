package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private String computerScience;

    public Programmer(String name, String surname, String profession, String education, String project, String computerScience) {
        super(name, surname, profession, education, project);
        this.computerScience = computerScience;
    }

    public String writeApp() {
        return super.makeProject();
    }
}
