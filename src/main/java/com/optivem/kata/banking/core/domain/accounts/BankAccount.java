package com.optivem.kata.banking.core.domain.accounts;

public class BankAccount {
    private final AccountNumber accountNumber;
    private final AccountHolderName accountHolderName;
    private int balance;

    public BankAccount(AccountNumber accountNumber, AccountHolderName accountHolderName, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this(other.getAccountNumber(), other.getAccountHolderName(), other.getBalance());
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public AccountHolderName getAccountHolderName() { return accountHolderName; }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
