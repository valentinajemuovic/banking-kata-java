package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

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

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if(amount > balance) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }

        balance -= amount;
    }
}
