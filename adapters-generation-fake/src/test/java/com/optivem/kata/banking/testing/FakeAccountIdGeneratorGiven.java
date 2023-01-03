package com.optivem.kata.banking.testing.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountIdGenerator;

public class FakeAccountIdGeneratorGiven {
    private final FakeAccountIdGenerator generator;

    public FakeAccountIdGeneratorGiven(FakeAccountIdGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(long bankAccountId) {
        generator.add(bankAccountId);
    }
}
