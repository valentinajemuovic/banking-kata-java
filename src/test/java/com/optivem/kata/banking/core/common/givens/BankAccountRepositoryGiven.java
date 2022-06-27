package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.gateways.BankAccountRepository;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountTestBuilder.bankAccount;

public class BankAccountRepositoryGiven {

    private final BankAccountRepository repository;

    public BankAccountRepositoryGiven(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void alreadyHasBankAccount(String accountNumber, int balance) {
        var bankAccount = bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        repository.add(bankAccount);
    }

    public void alreadyHasBankAccount(String accountNumber, String firstName, String lastName, int balance) {
        var bankAccount = bankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(balance)
                .build();

        repository.add(bankAccount);
    }
}
