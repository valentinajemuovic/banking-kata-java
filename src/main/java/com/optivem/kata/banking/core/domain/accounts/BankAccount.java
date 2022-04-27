package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

public class BankAccount {
    private final AccountNumber accountNumber;
    private final AccountHolderName accountHolderName;
    private Balance balance;

    public BankAccount(AccountNumber accountNumber, AccountHolderName accountHolderName, Balance balance) {
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

    public AccountHolderName getAccountHolderName() {
        return accountHolderName;
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(TransactionAmount amount) {
        balance = balance.add(amount);
    }

    public void withdraw(TransactionAmount amount) {
        if (amount.greaterThan(balance)) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }

        balance = balance.subtract(amount);
    }
}
