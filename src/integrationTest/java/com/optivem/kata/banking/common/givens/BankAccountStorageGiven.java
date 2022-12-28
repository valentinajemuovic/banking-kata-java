package com.optivem.kata.banking.common.givens;

import com.optivem.kata.banking.common.builders.ports.driven.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;

public class BankAccountStorageGiven {

    private final BankAccountStorage storage;

    public BankAccountStorageGiven(BankAccountStorage storage) {
        this.storage = storage;
    }

    public void alreadyHasBankAccount(String accountNumber, int balance) {
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);
    }

    public void alreadyHasBankAccount(String accountNumber, String firstName, String lastName, int balance) {
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);
    }
}
