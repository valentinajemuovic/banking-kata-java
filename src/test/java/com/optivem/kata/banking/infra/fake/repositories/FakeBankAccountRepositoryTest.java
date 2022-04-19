package com.optivem.kata.banking.infra.fake.repositories;

import com.optivem.kata.banking.core.domain.entities.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeBankAccountRepositoryTest {

    private FakeBankAccountRepository repository;

    @BeforeEach
    private void init() {
        this.repository = new FakeBankAccountRepository();
    }

    @Test
    void should_return_empty_result_when_account_number_does_not_exist() {
        var accountNumber = "GB36BARC20038032622823";
        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.empty());
    }

    @Test
    void show_return_bank_account_when_account_number_exists() {
        var accountNumber = "GB36BARC20038032622823";
        var bankAccount = new BankAccount(accountNumber);

        repository.add(bankAccount);

        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
    }
}
