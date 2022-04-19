package com.optivem.kata.banking.infra.fake.bankaccounts;

import com.optivem.kata.banking.core.common.Factory;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryMessages;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.optivem.kata.banking.core.common.Assertions.assertThrowsRepositoryException;
import static org.assertj.core.api.Assertions.assertThat;

class FakeBankAccountRepositoryTest {

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
        var bankAccount = Factory.createDefaultBankAccount(accountNumber);

        repository.add(bankAccount);

        var retrievedBankAccount = repository.find(accountNumber);
        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_add() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        var expectedBankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        repository.add(bankAccount);

        bankAccount.setBalance(60);

        var retrievedBankAccount = repository.find(accountNumber);

        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(expectedBankAccount));
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_find() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        var expectedBankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        repository.add(bankAccount);
        var retrievedBankAccount = repository.find(accountNumber);

        assertThat(retrievedBankAccount).isPresent();

        retrievedBankAccount.get().setBalance(20);

        var subsequentRetrievedBankAccount = repository.find(accountNumber);

        assertThat(subsequentRetrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(expectedBankAccount));
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_update() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        var expectedBankAccount = Factory.createDefaultBankAccount(accountNumber, balance);

        repository.add(bankAccount);
        var retrievedBankAccount = repository.find(accountNumber);

        assertThat(retrievedBankAccount).isPresent();

        repository.update(retrievedBankAccount.get());

        retrievedBankAccount.get().setBalance(10);

        var subsequentRetrievedBankAccount = repository.find(accountNumber);

        assertThat(subsequentRetrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(expectedBankAccount));
    }

    @Test
    void should_retrieve_updated_bank_account_after_update() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = Factory.createDefaultBankAccount(accountNumber, balance);
        var updateBalance = 10;
        var expectedUpdatedBankAccount = Factory.createDefaultBankAccount(accountNumber, updateBalance);

        repository.add(bankAccount);

        bankAccount.setBalance(10);

        repository.update(bankAccount);

        var retrievedBankAccount = repository.find(accountNumber);

        assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(expectedUpdatedBankAccount));
    }

    @Test
    void should_throw_exception_when_attempt_add_bank_account_with_same_account_number_twice() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var balance2 = 60;
        var bankAccount = Factory.createDefaultBankAccount(accountNumber, balance);
        var bankAccount2 = Factory.createDefaultBankAccount(accountNumber, balance2);

        repository.add(bankAccount);

        assertThrowsRepositoryException(() -> repository.add(bankAccount2), RepositoryMessages.REPOSITORY_CONSTRAINT_VIOLATION);
    }

    @Test
    void should_throw_exception_when_attempt_to_update_non_existent_bank_account() {
        var accountNumber = "GB36BARC20038032622823";
        var bankAccount = Factory.createDefaultBankAccount(accountNumber);

        assertThrowsRepositoryException(() -> repository.update(bankAccount), RepositoryMessages.REPOSITORY_CANNOT_UPDATE_NON_EXISTENT);
    }
}
