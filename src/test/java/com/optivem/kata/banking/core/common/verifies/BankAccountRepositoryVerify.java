package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountRepositoryVerify {

    private final BankAccountRepository repository;

    public BankAccountRepositoryVerify(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount contains(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();
        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
        return retrievedBankAccount.get();
    }

    public BankAccount contains(String accountNumber, int balance) {
        var expectedBankAccount = BankAccountBuilder.aBankAccount()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();

        return contains(expectedBankAccount);
    }

    public BankAccount contains(String accountNumber, String firstName, String lastName, int initialBalance) {
        var expectedBankAccount = BankAccountBuilder.aBankAccount()
                .accountNumber(accountNumber)
                .firstName(firstName)
                .lastName(lastName)
                .balance(initialBalance)
                .build();

        return contains(expectedBankAccount);
    }

    public BankAccount contains(String accountNumber) {
        var retrievedBankAccount = repository.find(AccountNumber.of(accountNumber));
        assertThat(retrievedBankAccount).usingRecursiveComparison().isNotEqualTo(Optional.empty());
        return retrievedBankAccount.get();
    }

    public void doesNotContain(String accountNumber) {
        var retrievedBankAccount = repository.find(AccountNumber.of(accountNumber));
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.empty());
    }
}
