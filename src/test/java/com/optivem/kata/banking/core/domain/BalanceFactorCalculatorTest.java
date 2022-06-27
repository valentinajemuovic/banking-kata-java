package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.scoring.BalanceFactorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountDtoTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class BalanceFactorCalculatorTest {
    private BalanceFactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        factorCalculator = new BalanceFactorCalculator();
    }

    @Test
    void should_return_value_of_balance_plus_5() {
        var balance = 10;

        var bankAccount = bankAccount()
                .withBalance(balance)
                .buildEntity();

        var expectedResult = 15;

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
