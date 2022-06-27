package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountDtoTestBuilder.bankAccount;

public class BankAccountStorageGiven {

    private final BankAccountStorage storage;

    public BankAccountStorageGiven(BankAccountStorage storage) {
        this.storage = storage;
    }

    public void alreadyHasBankAccount(String accountNumber, int balance) {
        var bankAccount = bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);
    }

    public void alreadyHasBankAccount(String accountNumber, String firstName, String lastName, int balance) {
        var bankAccount = bankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);
    }
}
