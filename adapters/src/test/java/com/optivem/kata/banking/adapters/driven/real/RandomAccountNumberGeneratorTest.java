package com.optivem.kata.banking.adapters.driven.real;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomAccountNumberGeneratorTest {
    private RandomAccountNumberGenerator generator;

    @BeforeEach
    private void init() {
        this.generator = new RandomAccountNumberGenerator();
    }

    @Test
    void should_generate_next() {
        var accountNumber = generator.next();
        assertThat(accountNumber).isNotNull();
    }

    @Test
    void should_generate_multiple_unique_next() {
        var accountNumber1 = generator.next();
        assertThat(accountNumber1).isNotNull();

        var accountNumber2 = generator.next();
        assertThat(accountNumber2).isNotNull();
        assertThat(accountNumber2).isNotEqualTo(accountNumber1);

        var accountNumber3 = generator.next();
        assertThat(accountNumber3).isNotNull();
        assertThat(accountNumber3).isNotEqualTo(accountNumber2);
    }
}
