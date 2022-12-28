package com.optivem.kata.banking.adapters.driven.real;

import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
public class JpaBankAccountStorageTest {
    private BankAccountStorage storage;
    private AccountIdGenerator accountIdGenerator;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public JpaBankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        this.accountIdGenerator = accountIdGenerator;
        this.storage = storage;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Test
    void should_return_empty_given_non_existent_account_number() {
        var accountNumber = accountNumberGenerator.next();
        var bankAccount = storage.find(accountNumber);
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

    private void shouldRetrieveAddedAccount(BankAccountDto bankAccount) {
        storage.add(bankAccount);

        var retrievedBankAccount = storage.find(bankAccount.getAccountNumber());

        assertThat(retrievedBankAccount).isNotNull();
        assertThat(retrievedBankAccount.get()).isEqualTo(bankAccount);
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
