package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.common.builders.entities.BankAccountTestBuilder;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountRepositoryVerify {

    private final BankAccountRepository repository;

    public BankAccountRepositoryVerify(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount shouldContain(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();
        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
        return retrievedBankAccount.get();
    }

    public BankAccount shouldContain(String accountNumber, int balance) {
        var expectedBankAccount = BankAccountTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        return shouldContain(expectedBankAccount);
    }

    public BankAccount shouldContain(String accountNumber, String firstName, String lastName, LocalDate openingDate, int initialBalance) {
        var expectedBankAccount = BankAccountTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withOpeningDate(openingDate)
                .withBalance(initialBalance)
                .build();

        return shouldContain(expectedBankAccount);
    }

    public BankAccount shouldContain(String accountNumber) {
        var retrievedBankAccount = repository.find(AccountNumber.of(accountNumber));
        assertThat(retrievedBankAccount).usingRecursiveComparison().isNotEqualTo(Optional.empty());
        return retrievedBankAccount.get();
    }

    public void shouldNotContain(String accountNumber) {
        var retrievedBankAccount = repository.find(AccountNumber.of(accountNumber));
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.empty());
    }
}
