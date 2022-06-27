package com.optivem.kata.banking.core.internal.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;

public class TimeFactorCalculator implements FactorCalculator {
    private final DateTimeService dateTimeService;

    public TimeFactorCalculator(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public int calculate(BankAccount bankAccount) {
        var openingDate = bankAccount.getOpeningDate();
        var currentDateTime = dateTimeService.now();
        var currentDate = currentDateTime.toLocalDate();
        var period = openingDate.until(currentDate);
        return period.getDays();
    }
}
