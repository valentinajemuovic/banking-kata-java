package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;

public class FakeAccountNumberGeneratorGiven {

    private FakeAccountNumberGenerator generator;

    public FakeAccountNumberGeneratorGiven(FakeAccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(String number) {
        var accountNumber = new AccountNumber(number);
        generator.add(accountNumber);
    }
}
