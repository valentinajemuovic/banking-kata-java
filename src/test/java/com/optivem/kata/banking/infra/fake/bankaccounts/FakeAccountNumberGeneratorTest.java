package com.optivem.kata.banking.infra.fake.bankaccounts;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
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
        assertThat(next).isEqualTo(AccountNumber.of(expectedValue));
    }

    private void assertNextThrowsException() {
        var exception = assertThrows(FakeException.class, () -> generator.next());
        assertThat(exception.getMessage()).isEqualTo(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
    }
}
