package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.*;
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
    private AccountIdGenerator accountIdGenerator;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public SqlBankAccountRepositoryTest(BankAccountRepository repository, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        this.accountIdGenerator = accountIdGenerator;
        this.repository = repository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Test
    void should_return_empty_given_non_existent_account_number() {
        var accountNumber = accountNumberGenerator.next();
        var bankAccount = repository.find(accountNumber);
        assertThat(bankAccount).isEqualTo(Optional.empty());
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

    private void shouldRetrieveAddedAccount(BankAccount bankAccount) {
        repository.add(bankAccount);

        var retrievedBankAccount = repository.find(bankAccount.getAccountNumber());

        assertThat(retrievedBankAccount).isNotNull();
        assertThat(retrievedBankAccount.get()).isEqualTo(bankAccount);
    }

    private BankAccount createSomeBankAccount() {
        var accountId = accountIdGenerator.next().toLong();
        var accountNumber = accountNumberGenerator.next().toString();
        return bankAccount()
                .withAccountId(accountId)
                .withAccountNumber(accountNumber)
                .build();
    }
}
