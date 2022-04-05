package ru.job4j.inheritance;

public class Profession {

    private String name;
    private String surname;
    private String profession;
    private String education;

    public Profession(String name, String surname, String profession, String education) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.education = education;
    }

    public Profession() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfession() {
        return profession;
    }

    public String getEducation() {
        return education;
    }

    public static void main(String[] args) {

    }
}
