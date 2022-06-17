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

    public static BankAccountBuilder bankAccount() {
        return new BankAccountBuilder();
    }

    private String accountNumber;
    private String firstName;
    private String lastName;
    private int balance;

    public BankAccountBuilder() {
        withAccountNumber(DEFAULT_ACCOUNT_NUMBER);
        withFirstName(DEFAULT_FIRST_NAME);
        withLastName(DEFAULT_LAST_NAME);
        withBalance(DEFAULT_BALANCE);
    }

    public BankAccountBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankAccountBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BankAccountBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BankAccountBuilder withBalance(int balance) {
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
