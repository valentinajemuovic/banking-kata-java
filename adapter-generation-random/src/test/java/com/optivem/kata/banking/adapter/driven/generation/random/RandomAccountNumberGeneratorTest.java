package com.optivem.kata.banking.adapter.driven.generation.random;

import com.optivem.kata.banking.adapter.driven.base.GeneratorTest;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;

class RandomAccountNumberGeneratorTest extends GeneratorTest<RandomAccountNumberGenerator> {

    @Override
    protected RandomAccountNumberGenerator create() {
        return new RandomAccountNumberGenerator();
    }
}
