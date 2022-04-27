package com.optivem.kata.banking.core.common.builders.entities;

import com.optivem.kata.banking.core.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.Balance;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class BankAccountBuilder {

    private static final String ACCOUNT_NUMBER = "GB10BARC20040184197751";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final int BALANCE = 100;

    private String accountNumber;
    private String firstName;
    private String lastName;
    private int balance;

    public BankAccountBuilder() {
        accountNumber(ACCOUNT_NUMBER);
        firstName(FIRST_NAME);
        lastName(LAST_NAME);
        balance(BALANCE);
    }

    public static BankAccountBuilder aBankAccount() {
        return new BankAccountBuilder();
    }

    public BankAccountBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
        var accountNumber = new AccountNumber(this.accountNumber);
        var accountHolderName = new AccountHolderName(firstName, lastName);
        var balance = new Balance(this.balance);
        return new BankAccount(accountNumber, accountHolderName, balance);
    }
}
