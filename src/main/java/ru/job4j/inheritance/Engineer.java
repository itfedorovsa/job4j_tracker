package ru.job4j.inheritance;

public class Engineer extends Profession {

    private String project;

    public Engineer(String name, String surname, String profession, String education, String project) {
        super(name, surname, profession, education);
        this.project = project;
    }

    public String makeProject() {
        return super.getEducation();
    }
}
