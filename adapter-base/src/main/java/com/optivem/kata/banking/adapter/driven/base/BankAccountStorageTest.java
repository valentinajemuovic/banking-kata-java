package com.optivem.kata.banking.adapter.driven.base;

import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class BankAccountStorageTest {
    private final BankAccountStorage storage;
    private final AccountIdGenerator accountIdGenerator;
    private final AccountNumberGenerator accountNumberGenerator;

    protected BankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        this.accountIdGenerator = accountIdGenerator;
        this.storage = storage;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Test
    void should_return_empty_given_non_existent_account_number() {
        var accountNumber = accountNumberGenerator.next();
        var bankAccount = storage.find(accountNumber);
        assertThat(bankAccount).isNotPresent();
    }

    @Test
    void should_return_added_bank_account() {
        var bankAccount = createSomeBankAccount();
        shouldRetrieveAddedAccount(bankAccount);
    }

    @Test
    void should_find_multiple_added_bank_accounts() {
        var bankAccount1 = createSomeBankAccount();
        shouldRetrieveAddedAccount(bankAccount1);

        var bankAccount2 = createSomeBankAccount();
        shouldRetrieveAddedAccount(bankAccount2);

        var bankAccount3 = createSomeBankAccount();
        shouldRetrieveAddedAccount(bankAccount3);
    }

    @Test
    void should_only_update_filled_attributes() {
        var newFirstName = "John";
        var newLastName = "Bunyan";
        var bankAccount = createSomeBankAccount();

        storage.add(bankAccount);

        var retrievedBankAccountAfterToSave = storage.find(bankAccount.getAccountNumber());
        retrievedBankAccountAfterToSave.ifPresent(bankAccountToUpdate -> {
            bankAccountToUpdate.setFirstName(newFirstName);
            bankAccountToUpdate.setLastName(newLastName);
            storage.update(bankAccountToUpdate);
        });
        var retrievedBankAccountAfterToUpdate = storage.find(bankAccount.getAccountNumber());

        assertThat(retrievedBankAccountAfterToUpdate).isPresent();
        assertThat(retrievedBankAccountAfterToUpdate.get().getFirstName()).isEqualTo(newFirstName);
        assertThat(retrievedBankAccountAfterToUpdate.get().getLastName()).isEqualTo(newLastName);
        assertThat(retrievedBankAccountAfterToUpdate.get().getBalance()).isEqualTo(bankAccount.getBalance());
        assertThat(retrievedBankAccountAfterToUpdate.get().getAccountId()).isEqualTo(bankAccount.getAccountId());
        assertThat(retrievedBankAccountAfterToUpdate.get().getAccountNumber()).isEqualTo(bankAccount.getAccountNumber());
        assertThat(retrievedBankAccountAfterToUpdate.get().getNationalIdentityNumber()).isEqualTo(bankAccount.getNationalIdentityNumber());
        assertThat(retrievedBankAccountAfterToUpdate.get().getOpeningDate()).isEqualTo(bankAccount.getOpeningDate());
    }

    private void shouldRetrieveAddedAccount(BankAccountDto bankAccount) {
        storage.add(bankAccount);

        var retrievedBankAccount = storage.find(bankAccount.getAccountNumber());

        assertThat(retrievedBankAccount).isNotNull();
        assertThat(retrievedBankAccount).contains(bankAccount);
    }

    private BankAccountDto createSomeBankAccount() {
        var accountId = accountIdGenerator.next();
        var accountNumber = accountNumberGenerator.next();
        return bankAccount()
                .withAccountId(accountId)
                .withAccountNumber(accountNumber)
                .build();
    }
}