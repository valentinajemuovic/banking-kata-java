package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;

public class FakeAccountNumberGeneratorGiven {
    private final FakeAccountNumberGenerator generator;

    public FakeAccountNumberGeneratorGiven(FakeAccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(String number) {
        var accountNumber = AccountNumber.of(number);
        generator.add(accountNumber);
    }
}
