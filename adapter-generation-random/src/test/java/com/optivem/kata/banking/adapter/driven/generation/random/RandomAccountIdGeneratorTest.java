package com.optivem.kata.banking.adapter.driven.generation.random;

import com.optivem.kata.banking.adapter.driven.base.GeneratorTest;

public class RandomAccountIdGeneratorTest extends GeneratorTest<RandomAccountIdGenerator> {

    @Override
    protected RandomAccountIdGenerator create() {
        return new RandomAccountIdGenerator();
    }
}
