package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import java.util.Optional;

import static com.optivem.kata.banking.core.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountRepositoryAssert {

    private final BankAccountRepository repository;

    public BankAccountRepositoryAssert(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void containsBankAccount(String accountNumber, int balance) {
        var expectedBankAccount = aBankAccount()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();

        containsBankAccount(expectedBankAccount);
    }

    public void containsBankAccount(String accountNumber, String firstName, String lastName, int initialBalance) {
        var expectedBankAccount = aBankAccount()
                .accountNumber(accountNumber)
                .firstName(firstName)
                .lastName(lastName)
                .balance(initialBalance)
                .build();

        containsBankAccount(expectedBankAccount);
    }

    private void containsBankAccount(BankAccount expectedBankAccount) {
        var accountNumber = expectedBankAccount.getAccountNumber();
        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(expectedBankAccount));
    }

}
