package com.optivem.kata.banking.infra.fake.bankaccounts;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.givens.Givens.givenThatGenerator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FakeAccountNumberGeneratorTest {
    private FakeAccountNumberGenerator generator;

    @BeforeEach
    void init() {
        this.generator = new FakeAccountNumberGenerator();
    }

    @Test
    void should_throw_exception_when_no_elements() {
        var exception = assertThrows(FakeException.class, () -> generator.next());

        assertThat(exception.getMessage()).isEqualTo(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
    }

    @Test
    void should_return_next_element_when_there_is_one_element() {
        var expectedValue = "GB54BARC20032611545669";

        givenThatGenerator(generator).willGenerate(expectedValue);

        assertNextEquals(expectedValue);
        assertNextThrowsException();
    }

    @Test
    void should_return_next_elements_when_there_are_multiple_elements() {
        var expectedValue1 = "GB54BARC20032611545669";
        var expectedValue2 = "GB36BARC20038032622823";
        var expectedValue3 = "GB10BARC20040184197751";

        givenThatGenerator(generator).willGenerate(expectedValue1);
        givenThatGenerator(generator).willGenerate(expectedValue2);
        givenThatGenerator(generator).willGenerate(expectedValue3);

        assertNextEquals(expectedValue1);
        assertNextEquals(expectedValue2);
        assertNextEquals(expectedValue3);
        assertNextThrowsException();
    }

    private void assertNextEquals(String expectedValue) {
        var next = generator.next();
        assertThat(next).isEqualTo(new AccountNumber(expectedValue));
    }

    private void assertNextThrowsException() {
        var exception = assertThrows(FakeException.class, () -> generator.next());
        assertThat(exception.getMessage()).isEqualTo(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
    }
}
