package com.optivem.kata.banking.adapter.driven.generation.fake;

import com.optivem.kata.banking.adapter.driven.base.GeneratorTest;
import com.optivem.kata.banking.adapter.driven.generation.fake.internal.NextElementIsNotSetupException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeAccountNumberGeneratorTest extends GeneratorTest<FakeAccountNumberGenerator> {

    @Override
    protected FakeAccountNumberGenerator create() {

        var generator = new FakeAccountNumberGenerator();

        var expectedValue1 = "GB54BARC20032611545669";
        var expectedValue2 = "GB36BARC20038032622823";
        var expectedValue3 = "GB10BARC20040184197751";

        generator.setupNext(expectedValue1);
        generator.setupNext(expectedValue2);
        generator.setupNext(expectedValue3);

        return generator;
    }

    @Test
    void shouldReturnsSetupValuesInOrder() {
        var generator = create();

        assertEquals("GB54BARC20032611545669", generator.next());
        assertEquals("GB36BARC20038032622823", generator.next());
        assertEquals("GB10BARC20040184197751", generator.next());
    }

    @Test
    void shouldThrowsExceptionWhenNoMoreValues() {
        var generator = create();
        generator.next();
        generator.next();
        generator.next();

        assertThrows(NextElementIsNotSetupException.class, generator::next);
    }

    @Test
    void shouldAddsValuesToQueue() {
        var generator = new FakeAccountNumberGenerator();
        var newValue = "GB72BARC20041512345678";

        generator.setupNext(newValue);
        assertEquals(newValue, generator.next());
    }
}
