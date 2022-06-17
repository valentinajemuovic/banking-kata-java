package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;

public class BankAccountRepositoryGiven {

    private final BankAccountRepository repository;

    public BankAccountRepositoryGiven(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void alreadyHasBankAccount(String accountNumber, int balance) {
        var bankAccount = aBankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        repository.add(bankAccount);
    }

    public void alreadyHasBankAccount(String accountNumber, String firstName, String lastName, int balance) {
        var bankAccount = aBankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(balance)
                .build();

        repository.add(bankAccount);
    }
}
