package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> account = new HashMap<>();
        account.put("itfedorovsa@gmail.com", "Fedorov Sergey Andreevich");
        account.put("example@gmail.com", "Paul Walker");
        for (String acc : account.keySet()) {
            System.out.println(account.get(acc));
        }
    }
}
