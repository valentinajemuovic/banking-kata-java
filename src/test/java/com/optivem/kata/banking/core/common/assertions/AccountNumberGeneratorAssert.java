package com.optivem.kata.banking.core.common.assertions;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountNumberGeneratorAssert {

    private final AccountNumberGenerator generator;

    public AccountNumberGeneratorAssert(AccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void generatesNext(String expectedValue) {
        var next = generator.next();
        assertThat(next).isEqualTo(AccountNumber.of(expectedValue));
    }
}
