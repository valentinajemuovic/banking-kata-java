package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;
import java.util.UUID;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
public class SqlBankAccountRepositoryTest {
    private BankAccountRepository repository;

    @Autowired
    public SqlBankAccountRepositoryTest(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Test
    void should_return_empty_given_non_existent_account_number() {
        var accountNumber = AccountNumber.of("1234abc");
        var bankAccount = repository.find(accountNumber);
        assertThat(bankAccount).isEqualTo(Optional.empty());
    }

    @Test
    void should_return_added_bank_account() {
        var accountNumber = UUID.randomUUID().toString();
        var bankAccount = bankAccount()
                .withAccountNumber(accountNumber)
                .build();

        repository.add(bankAccount);

        var retrievedBankAccount = repository.find(bankAccount.getAccountNumber());

        assertThat(retrievedBankAccount).isNotNull();
        assertThat(retrievedBankAccount.get()).isEqualTo(bankAccount);
    }
}
