package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.infra.fake.FakeAccountIdGenerator;

public class FakeAccountIdGeneratorGiven {
    private final FakeAccountIdGenerator generator;

    public FakeAccountIdGeneratorGiven(FakeAccountIdGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(long bankAccountId) {
        generator.add(bankAccountId);
    }
}
