package com.optivem.kata.banking.core.common.builders.entities;

import com.optivem.kata.banking.core.domain.accounts.*;

import java.time.LocalDate;

public class BankAccountTestBuilder {
    public static BankAccountTestBuilder bankAccount() {
        return new BankAccountTestBuilder();
    }

    private long accountId;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;

    public BankAccountTestBuilder() {
        this.accountId = BankAccountDefaults.DEFAULT_ACCOUNT_ID;
        this.accountNumber = BankAccountDefaults.DEFAULT_ACCOUNT_NUMBER;
        this.firstName = BankAccountDefaults.DEFAULT_FIRST_NAME;
        this.lastName = BankAccountDefaults.DEFAULT_LAST_NAME;
        this.openingDate = BankAccountDefaults.DEFAULT_OPENING_DATE;
        this.balance = BankAccountDefaults.DEFAULT_BALANCE;
    }

    public BankAccountTestBuilder withAccountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    public BankAccountTestBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankAccountTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BankAccountTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BankAccountTestBuilder withOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public BankAccountTestBuilder withBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        var accountId = AccountId.of(this.accountId);
        var accountNumber = AccountNumber.of(this.accountNumber);
        var accountHolderName = AccountHolderName.of(firstName, lastName);
        var balance = Balance.of(this.balance);

        return BankAccountBuilder.bankAccount()
                .withAccountId(accountId)
                .withAccountNumber(accountNumber)
                .withAccountHolderName(accountHolderName)
                .withOpeningDate(openingDate)
                .withBalance(balance)
                .build();
    }


}
