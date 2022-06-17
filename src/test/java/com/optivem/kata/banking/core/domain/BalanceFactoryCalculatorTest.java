package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.BalanceFactorCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.NameFactorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class BalanceFactoryCalculatorTest {
    private BalanceFactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        factorCalculator = new BalanceFactorCalculator();
    }

    @Test
    void should_return_value_of_balance_plus_5() {
        var balance = 10;

        var bankAccount = aBankAccount()
                .balance(balance)
                .build();

        var expectedResult = 15;

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
