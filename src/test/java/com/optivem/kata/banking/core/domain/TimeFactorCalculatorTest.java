package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.BalanceFactorCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.TimeFactorCalculator;
import com.optivem.kata.banking.infra.fake.time.FakeDateTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeFactorCalculatorTest {
    private FakeDateTimeService dateTimeService;
    private TimeFactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        dateTimeService = new FakeDateTimeService();
        factorCalculator = new TimeFactorCalculator(dateTimeService);
    }

    @Test
    void should_return_number_of_days_since_opening_date() {
        var currentDateTime = LocalDateTime.of(2022, 4, 20, 10, 15);
        var openingDate = LocalDate.of(2022, 4, 15);

        dateTimeService.add(currentDateTime);

        var bankAccount = bankAccount()
                .withOpeningDate(openingDate)
                .build();

        var expectedResult = 5;

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
