package ru.job4j.inheritance;

public class Builder extends Engineer {

    private String construction;

    public Builder(String name, String surname, String profession, String education, String project, String construction) {
        super(name, surname, profession, education, project);
        this.construction = construction;
    }

    public Builder(String construction) {
        this.construction = construction;
    }

    public Builder() {
    }

    public String buildHouse() {
        return "I can build a house";
    }

    public String makeProject() {
        return super.makeProject();
    }
}
