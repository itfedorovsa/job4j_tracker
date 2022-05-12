package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankService {

    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(owner -> owner.getPassport().equals(passport))
                .findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        Optional<Account> account = Optional.empty();
        if (user.isPresent()) {
            account = users.get(user.get())
                    .stream()
                    .filter(acc -> acc.getRequisite().equals(requisite))
                    .findFirst();
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount.isPresent() && destAccount.isPresent() && srcAccount.get().getBalance() >= amount) {
            srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BankService bank = new BankService();
        bank.addUser(new User("321", "Petr Arsentev"));
        Optional<User> user = bank.findByPassport("3211");
        user.ifPresent(value -> System.out.println(value.getUsername()));
    }
}