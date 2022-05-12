package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class describes the operation of the banking system (a service for creating banking accounts,
 * storing and transferring money between banking accounts).
 */
public class BankService {

    /**
     * The field contains a database of bank customers with all of their accounts.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * The method adds a user to the system.
     * @param user a new user to add to the system.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * The method adds an invoice to an existing user.
     * @param passport the account is added according to passport data.
     * @param account account number to add.
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * The method searches for a bank customer according to passport data.
     * @param passport passport data of the client.
     * @return returns an existing bank customer or null if the customer is not found.
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(owner -> owner.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * The method searches for the bank customer's account by account details.
     * @param passport паспортные данные (номер) клиента.
     * @param requisite account details.
     * @return returns the account of an existing bank customer or null if the customer is not found.
     */
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

    /**
     * The method transfers money from one account to another between bank clients,
     * or between accounts of one client.
     * @param srcPassport client's passport data of the client FROM whom the transfer is made
     * @param srcRequisite account details FROM which the transfer is made
     * @param destPassport client's passport data TO WHOM the transfer is made
     * @param destRequisite account details TO WHICH the transfer is made
     * @param amount transfer amount
     * @return returns true if the transfer is successful, and false if any account does not exist,
     * or there is not enough money on the account from which the transfer is made.
     */
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