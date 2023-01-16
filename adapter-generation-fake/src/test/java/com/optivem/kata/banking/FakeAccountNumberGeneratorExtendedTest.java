package com.optivem.kata.banking;

import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.adapters.driven.fake.internal.NextElementIsNotSetupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.testing.Givens.givenThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeAccountNumberGeneratorExtendedTest {
    private FakeAccountNumberGenerator generator;

    @BeforeEach
    void init() {
        this.generator = new FakeAccountNumberGenerator();
    }

    @Test
    void should_throw_exception_when_no_elements() {
        assertThrows(NextElementIsNotSetupException.class, () -> generator.next());
    }

    @Test
    void should_return_next_element_when_there_is_one_element() {
        var expectedValue = "GB54BARC20032611545669";

        givenThat(generator).willGenerate(expectedValue);

        assertGeneratesNext(expectedValue);

        assertNextThrowsException();
    }

    @Test
    void should_return_next_elements_when_there_are_multiple_elements() {
        var expectedValue1 = "GB54BARC20032611545669";
        var expectedValue2 = "GB36BARC20038032622823";
        var expectedValue3 = "GB10BARC20040184197751";

        givenThat(generator).willGenerate(expectedValue1);
        givenThat(generator).willGenerate(expectedValue2);
        givenThat(generator).willGenerate(expectedValue3);

        assertGeneratesNext(expectedValue1);
        assertGeneratesNext(expectedValue2);
        assertGeneratesNext(expectedValue3);

        assertNextThrowsException();
    }

    private void assertGeneratesNext(String expectedValue) {
        var next = generator.next();
        assertThat(next).isEqualTo(expectedValue);
    }

    private void assertNextThrowsException() {
        assertThrows(NextElementIsNotSetupException.class, () -> generator.next());
    }
}
