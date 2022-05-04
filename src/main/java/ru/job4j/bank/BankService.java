package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
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
    public User findByPassport(String passport) {
        for (User owner : users.keySet()) {
            if (owner.getPassport().equals(passport)) {
                return owner;
            }
        }
        return null;
    }

    /**
     * The method searches for the bank customer's account by account details.
     * @param passport паспортные данные (номер) клиента.
     * @param requisite account details.
     * @return returns the account of an existing bank customer or null if the customer is not found.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account acc : users.get(user)) {
                if (acc.getRequisite().equals(requisite)) {
                    return acc;
                }
            }
        }
        return null;
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
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return false;
    }
}
