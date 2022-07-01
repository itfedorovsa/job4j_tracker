package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsagePackageDate {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current date and time before formatting: " + currentDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTimeFormat = currentDateTime.format(formatter);
        System.out.println("Current date and time after formatting: " + currentDateTimeFormat);
    }
}
