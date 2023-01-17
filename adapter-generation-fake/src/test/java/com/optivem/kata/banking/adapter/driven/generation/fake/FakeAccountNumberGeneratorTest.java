package com.optivem.kata.banking.adapter.driven.generation.fake;

import com.optivem.kata.banking.adapter.driven.base.AccountNumberGeneratorTest;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;

class FakeAccountNumberGeneratorTest extends AccountNumberGeneratorTest {

    @Override
    protected AccountNumberGenerator create() {

        var generator = new FakeAccountNumberGenerator();

        var expectedValue1 = "GB54BARC20032611545669";
        var expectedValue2 = "GB36BARC20038032622823";
        var expectedValue3 = "GB10BARC20040184197751";

        generator.setupNext(expectedValue1);
        generator.setupNext(expectedValue2);
        generator.setupNext(expectedValue3);

        return generator;
    }
}
