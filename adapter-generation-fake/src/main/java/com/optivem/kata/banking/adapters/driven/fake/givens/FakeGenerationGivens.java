package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;

public class FakeGenerationGivens {
    public static FakeAccountIdGeneratorGiven givenThat(FakeAccountIdGenerator generator) {
        return new FakeAccountIdGeneratorGiven(generator);
    }

    public static FakeAccountNumberGeneratorGiven givenThat(FakeAccountNumberGenerator generator) {
        return new FakeAccountNumberGeneratorGiven(generator);
    }
}
