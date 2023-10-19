package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountTestConverter;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.BalanceFactorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceFactorCalculatorTest {
    private BalanceFactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        factorCalculator = new BalanceFactorCalculator();
    }

    @Test
    void should_return_value_of_balance_plus_5() {
        var balance = 10;

        var bankAccountDto = BankAccountDtoTestBuilder.bankAccount()
                .withBalance(balance)
                .build();

        var bankAccount = BankAccountTestConverter.toEntity(bankAccountDto);
        var expectedResult = 15;
        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
