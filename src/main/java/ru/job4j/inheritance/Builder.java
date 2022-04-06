package ru.job4j.inheritance;

public class Builder extends Engineer {

    private String construction;

    public Builder(String name, String surname, String profession, String education, String project, String construction) {
        super(name, surname, profession, education, project);
        this.construction = construction;
    }

    public String kindOfHouse() {
        return super.makeProject();
    }
}
