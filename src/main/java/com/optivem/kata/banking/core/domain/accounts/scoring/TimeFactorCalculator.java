package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.domain.time.DateTimeService;

public class TimeFactorCalculator implements FactorCalculator {
    private final DateTimeService dateTimeService;

    public TimeFactorCalculator(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public int calculate(BankAccount bankAccount) {
        var openingDate = bankAccount.getOpeningDate();
        var currentDateTime = dateTimeService.getCurrent();
        var currentDate = currentDateTime.toLocalDate();
        var period = openingDate.until(currentDate);
        return period.getDays();
    }
}
