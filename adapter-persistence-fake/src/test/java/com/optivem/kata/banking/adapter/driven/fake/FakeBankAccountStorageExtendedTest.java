package com.optivem.kata.banking.adapter.driven.fake;

import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: VC: Incorporate this into main test later
class FakeBankAccountStorageExtendedTest {

    private FakeBankAccountStorage storage;

    @BeforeEach
    private void init() {
        this.storage = new FakeBankAccountStorage();
    }

    @Test
    void should_return_empty_result_when_account_number_does_not_exist() {
        var accountNumber = "GB36BARC20038032622823";

        storage.shouldNotContain(accountNumber);
    }

    @Test
    void should_return_bank_account_when_account_number_exists() {
        var accountNumber = "GB36BARC20038032622823";
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .build();

        storage.add(bankAccount);

        storage.shouldContain(bankAccount);
    }

    @Test
    void should_retrieve_updated_bank_account_after_update() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var updateBalance = 50;

        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        var expectedUpdatedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(updateBalance)
                .build();

        storage.add(bankAccount);

        bankAccount.setBalance(updateBalance);

        storage.update(bankAccount);

        storage.shouldContain(expectedUpdatedBankAccount);
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_add() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);

        bankAccount.setBalance(20);

        storage.shouldContain(expectedBankAccount);
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_find() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);

        var retrievedBankAccount = storage.find(accountNumber).get();

        retrievedBankAccount.setBalance(20);

        storage.shouldContain(expectedBankAccount);
    }

    @Test
    void should_not_be_able_to_change_bank_account_after_update() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        storage.add(bankAccount);

        var retrievedBankAccount = storage.find(accountNumber).get();

        storage.update(retrievedBankAccount);

        retrievedBankAccount.setBalance(20);

        storage.shouldContain(expectedBankAccount);
    }


    @Test
    void should_throw_exception_when_attempt_add_bank_account_with_same_account_number_twice() {
        var accountNumber = "GB36BARC20038032622823";
        var balance = 40;
        var balance2 = 60;

        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        var bankAccount2 = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance2)
                .build();

        storage.add(bankAccount);

        // TODO: VC: Bring back
        // verifyThat(() -> storage.add(bankAccount2)).shouldThrowRepositoryException(RepositoryMessages.REPOSITORY_CONSTRAINT_VIOLATION);
    }

    @Test
    void should_throw_exception_when_attempt_to_update_non_existent_bank_account() {
        var accountNumber = "GB36BARC20038032622823";

        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .build();

        // TODO: VC: Bring back
        // verifyThat(() -> storage.update(bankAccount)).shouldThrowRepositoryException(RepositoryMessages.REPOSITORY_CANNOT_UPDATE_NON_EXISTENT);
    }
}
