package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printData() {
        System.out.println("active: " + active);
        System.out.println("status: " + status);
        System.out.println("message: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error mistake = new Error(true, 1, "ready");
        Error bug = new Error(false, 0, "terminated");
        Error unknown = new Error(false, 2, "server unavailable");
        error.printData();
        mistake.printData();
        bug.printData();
        unknown.printData();
    }

}
