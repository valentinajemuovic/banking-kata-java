package com.optivem.kata.banking.testing;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.testing.givens.FakeAccountIdGeneratorGiven;
import com.optivem.kata.banking.testing.givens.FakeAccountNumberGeneratorGiven;

public class Givens {

    public static FakeAccountNumberGeneratorGiven givenThat(FakeAccountNumberGenerator generator) {
        return new FakeAccountNumberGeneratorGiven(generator);
    }

    public static FakeAccountIdGeneratorGiven givenThat(FakeAccountIdGenerator generator) {
        return new FakeAccountIdGeneratorGiven(generator);
    }
}
