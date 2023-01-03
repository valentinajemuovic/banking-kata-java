package com.optivem.kata.banking.adapters.driven.fake.verifies;

import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountStorageVerify {

    private final BankAccountStorage storage;

    public BankAccountStorageVerify(BankAccountStorage storage) {
        this.storage = storage;
    }

    public BankAccountDto shouldContain(BankAccountDto bankAccount) {
        var accountNumber = bankAccount.getAccountNumber();
        var retrievedBankAccount = storage.find(accountNumber);
        Assertions.assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.of(bankAccount));
        return retrievedBankAccount.get();
    }

    public BankAccountDto shouldContain(String accountNumber, int balance) {
        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(balance)
                .build();

        return shouldContain(expectedBankAccount);
    }

    public BankAccountDto shouldContain(long accountId, String accountNumber, String firstName, String lastName, LocalDate openingDate, int initialBalance) {
        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountId(accountId)
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withOpeningDate(openingDate)
                .withBalance(initialBalance)
                .build();

        return shouldContain(expectedBankAccount);
    }

    public BankAccountDto shouldContain(String accountNumber) {
        var retrievedBankAccount = storage.find(accountNumber);
        Assertions.assertThat(retrievedBankAccount).usingRecursiveComparison().isNotEqualTo(Optional.empty());
        return retrievedBankAccount.get();
    }

    public void shouldNotContain(String accountNumber) {
        var retrievedBankAccount = storage.find(accountNumber);
        Assertions.assertThat(retrievedBankAccount).usingRecursiveComparison().isEqualTo(Optional.empty());
    }
}
