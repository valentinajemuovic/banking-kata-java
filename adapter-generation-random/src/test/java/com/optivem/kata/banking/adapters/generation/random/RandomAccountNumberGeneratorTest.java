package com.optivem.kata.banking.adapters.generation.random;

import com.optivem.kata.banking.adapter.driven.AccountNumberGeneratorTest;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;

class RandomAccountNumberGeneratorTest extends AccountNumberGeneratorTest {

    @Override
    protected AccountNumberGenerator create() {
        return new RandomAccountNumberGenerator();
    }

    /*
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

     */
}
