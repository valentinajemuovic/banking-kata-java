package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountNumberGeneratorVerify {

    private final AccountNumberGenerator generator;

    public AccountNumberGeneratorVerify(AccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void generatesNext(String expectedValue) {
        var next = generator.next();
        assertThat(next).isEqualTo(AccountNumber.of(expectedValue));
    }
}
