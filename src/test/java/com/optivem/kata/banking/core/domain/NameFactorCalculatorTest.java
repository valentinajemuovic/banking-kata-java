package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.FactorCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.NameFactorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class NameFactorCalculatorTest {
    private FactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        factorCalculator = new NameFactorCalculator();
    }

    @Test
    void should_return_5_given_total_name_length_is_less_than_or_equal_to_5() {
        var bankAccount = aBankAccount()
                .firstName("M")
                .lastName("S")
                .build();

        var expectedResult = 5;

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
