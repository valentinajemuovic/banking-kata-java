package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class Factory {

    private static final String FIRST_NAME = "JOHN";
    private static final String LAST_NAME = "SMITH";
    private static final int BALANCE = 100;

    public static BankAccount createDefaultBankAccount(String accountNumber, int balance) {
        return new BankAccount(new AccountNumber(accountNumber), FIRST_NAME, LAST_NAME, balance);
    }

    public static BankAccount createDefaultBankAccount(String accountNumber) {
        return createDefaultBankAccount(accountNumber, BALANCE);
    }

    public static BankAccount createBankAccount(String accountNumber, String firstName, String lastName, int balance) {
        return new BankAccount(new AccountNumber(accountNumber), firstName, lastName, balance);
    }
}
