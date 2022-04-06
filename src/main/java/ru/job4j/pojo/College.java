package ru.job4j.pojo;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Fedorov Sergey Andreevich");
        student.setGroup("Trainee");
        student.setStartDate("22.03.2022");
        System.out.println(student.getFullName() + " has a status " + student.getGroup() + ". Date of enter: "
                + student.getStartDate() + ".");
    }
}
