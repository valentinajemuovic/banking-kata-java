package com.optivem.kata.banking.core.domain.accounts;

public class BankAccount {
    private final String accountNumber;
    private int balance;

    public BankAccount(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this(other.getAccountNumber(), other.getBalance());
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
}
