package com.optivem.kata.banking.adapter.driven.generation.fake;

import com.optivem.kata.banking.adapter.driven.base.GeneratorTest;

public class FakeAccountIdGeneratorTest extends GeneratorTest<FakeAccountIdGenerator> {

    @Override
    protected FakeAccountIdGenerator create() {

        var generator = new FakeAccountIdGenerator();

        var expectedValue1 = 1001L;
        var expectedValue2 = 1002L;
        var expectedValue3 = 1003L;

        generator.setupNext(expectedValue1);
        generator.setupNext(expectedValue2);
        generator.setupNext(expectedValue3);

        return generator;
    }
}