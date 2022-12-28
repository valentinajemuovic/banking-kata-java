package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;

public class FakeAccountNumberGeneratorGiven {
    private final FakeAccountNumberGenerator generator;

    public FakeAccountNumberGeneratorGiven(FakeAccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(String accountNumber) {
        generator.add(accountNumber);
    }
}
