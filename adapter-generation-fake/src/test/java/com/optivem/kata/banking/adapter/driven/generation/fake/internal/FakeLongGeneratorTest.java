package com.optivem.kata.banking.adapter.driven.generation.fake.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FakeLongGeneratorTest {
    private FakeGenerator<Long> generator;

    @BeforeEach
    void init() {
        this.generator = new FakeGenerator<>();
    }

    @Test
    void should_throw_exception_when_no_elements() {
        assertThrows(NextElementIsNotSetupException.class, () -> generator.next());
    }

    @Test
    void should_return_next_element_when_there_is_one_element() {
        var expectedValue = 1001L;
        generator.setupNext(expectedValue);

        assertGeneratesNext(expectedValue);
        assertNextThrowsException();
    }

    @Test
    void should_return_next_elements_when_there_are_multiple_elements() {
        var expectedValue1 = 1001L;
        var expectedValue2 = 1002L;
        var expectedValue3 = 1003L;

        generator.setupNext(expectedValue1);
        generator.setupNext(expectedValue2);
        generator.setupNext(expectedValue3);

        assertGeneratesNext(expectedValue1);
        assertGeneratesNext(expectedValue2);
        assertGeneratesNext(expectedValue3);

        assertNextThrowsException();
    }

    private void assertGeneratesNext(Long expectedValue) {
        var next = generator.next();
        assertThat(next).isEqualTo(expectedValue);
    }

    private void assertNextThrowsException() {
        assertThrows(NextElementIsNotSetupException.class, () -> generator.next());
    }
}
