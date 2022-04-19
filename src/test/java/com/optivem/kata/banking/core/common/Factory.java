package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class Factory {

    private static final String FIRST_NAME = "JOHN";
    private static final String LAST_NAME = "SMITH";
    private static final int BALANCE = 100;

    public static BankAccount createDefaultBankAccount(String accountNumber, int balance) {
        return new BankAccount(accountNumber, FIRST_NAME, LAST_NAME, balance);
    }

    public static BankAccount createDefaultBankAccount(String accountNumber) {
        return createDefaultBankAccount(accountNumber, BALANCE);
    }
}
