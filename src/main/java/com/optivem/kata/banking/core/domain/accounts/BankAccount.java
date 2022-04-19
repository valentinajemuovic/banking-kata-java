package com.optivem.kata.banking.core.domain.accounts;

public class BankAccount {
    private final String accountNumber;
    private final String firstName;
    private final String lastName;
    private int balance;

    public BankAccount(String accountNumber, String firstName, String lastName, int balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this(other.getAccountNumber(), other.getFirstName(), other.getLastName(), other.getBalance());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
