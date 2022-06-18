package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlBankAccountRepositoryTest {
    private SqlBankAccountRepository repository;

    @BeforeEach
    private void init() {
        this.repository = new SqlBankAccountRepository();
    }

    @Test
    void should_return_empty_given_non_existent_account_number() {
        var accountNumber = AccountNumber.of("1234abc");
        var bankAccount = repository.find(accountNumber);
        assertThat(bankAccount).isEqualTo(Optional.empty());
    }
}
