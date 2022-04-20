package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import static com.optivem.kata.banking.core.builders.entities.BankAccountBuilder.aBankAccount;

public class BankAccountRepositoryGiven {

    private final BankAccountRepository repository;

    public BankAccountRepositoryGiven(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void containsBankAccount(String accountNumber, int balance) {
        var bankAccount = aBankAccount()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();

        repository.add(bankAccount);
    }
}
