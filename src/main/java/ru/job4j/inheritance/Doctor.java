package ru.job4j.inheritance;

public class Doctor extends Profession {

    private String healing;

    public Doctor(String name, String surname, String profession, String education, String healing) {
        super(name, surname, profession, education);
        this.healing = healing;
    }

    public String knowHealing() {
        return super.getEducation();
    }
}
