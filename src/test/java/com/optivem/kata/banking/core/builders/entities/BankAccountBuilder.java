package com.optivem.kata.banking.core.builders.entities;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class BankAccountBuilder {

    private static final String ACCOUNT_NUMBER = "GB10BARC20040184197751";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final int BALANCE = 100;

    private AccountNumber accountNumber;
    private String firstName;
    private String lastName;
    private int balance;

    public static BankAccountBuilder aBankAccount() {
        return new BankAccountBuilder();
    }

    public BankAccountBuilder() {
        accountNumber(ACCOUNT_NUMBER);
        firstName(FIRST_NAME);
        lastName(LAST_NAME);
        balance(BALANCE);
    }

    public BankAccountBuilder accountNumber(String accountNumber) {
        this.accountNumber = new AccountNumber(accountNumber);
        return this;
    }

    public BankAccountBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BankAccountBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BankAccountBuilder balance(int balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(accountNumber, firstName, lastName, balance);
    }
}
