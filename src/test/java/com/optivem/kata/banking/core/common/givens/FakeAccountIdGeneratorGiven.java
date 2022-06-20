package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.infra.fake.FakeAccountIdGenerator;

public class FakeAccountIdGeneratorGiven {
    private final FakeAccountIdGenerator generator;

    public FakeAccountIdGeneratorGiven(FakeAccountIdGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(long value) {
        var bankAccountId = AccountId.of(value);
        generator.add(bankAccountId);
    }
}
