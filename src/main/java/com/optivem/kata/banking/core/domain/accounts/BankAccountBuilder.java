package com.optivem.kata.banking.core.domain.accounts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankAccountBuilder {
    public static BankAccountBuilder bankAccount() {
        return new BankAccountBuilder();
    }

    private AccountNumber accountNumber;
    private AccountHolderName accountHolderName;
    private LocalDate openingDate;
    private Balance balance;

    public BankAccountBuilder withAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankAccountBuilder withAccountHolderName(AccountHolderName accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public BankAccountBuilder withOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public BankAccountBuilder withBalance(Balance balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(accountNumber, accountHolderName, openingDate, balance);
    }
}
