package com.optivem.kata.banking.core.domain.entities;

public class BankAccount {
    private String accountNumber;
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
