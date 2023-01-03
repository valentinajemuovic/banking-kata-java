package com.optivem.kata.banking.adapters.driven;

import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AccountNumberGeneratorTest {
    protected AccountNumberGenerator generator;

    @BeforeEach
    private void init() {
        this.generator = create();
    }

    protected abstract AccountNumberGenerator create();

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
