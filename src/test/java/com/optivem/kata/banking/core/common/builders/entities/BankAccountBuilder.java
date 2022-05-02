package com.optivem.kata.banking.core.common.builders.entities;

import com.optivem.kata.banking.core.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.Balance;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class BankAccountBuilder {

    private static final String DEFAULT_ACCOUNT_NUMBER = "GB10BARC20040184197751";
    private static final String DEFAULT_FIRST_NAME = "John";
    private static final String DEFAULT_LAST_NAME = "Smith";
    private static final int DEFAULT_BALANCE = 100;

    public static BankAccountBuilder aBankAccount() {
        return new BankAccountBuilder();
    }

    private String accountNumber;
    private String firstName;
    private String lastName;
    private int balance;

    public BankAccountBuilder() {
        accountNumber(DEFAULT_ACCOUNT_NUMBER);
        firstName(DEFAULT_FIRST_NAME);
        lastName(DEFAULT_LAST_NAME);
        balance(DEFAULT_BALANCE);
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
        var accountNumber = AccountNumber.of(this.accountNumber);
        var accountHolderName = AccountHolderName.of(firstName, lastName);
        var balance = Balance.of(this.balance);
        return new BankAccount(accountNumber, accountHolderName, balance);
    }
}
