package com.optivem.kata.banking.core.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;

public class DefaultScoreCalculator implements ScoreCalculator {

    private FactorAggregator factorAggregator;

    public DefaultScoreCalculator(FactorAggregator factorAggregator) {
        this.factorAggregator = factorAggregator;
    }

    @Override
    public Score calculate(BankAccount bankAccount) {
        var aggregationResult = factorAggregator.aggregate(bankAccount);

        if (aggregationResult <= 0) {
            return Score.C;
        }

        if (aggregationResult % 2 == 0) {
            return Score.B;
        }

        return Score.A;
    }

    public static DefaultScoreCalculator create(DateTimeService dateTimeService) {
        var nameFactorCalculator = new NameFactorCalculator();
        var balanceFactorCalculator = new BalanceFactorCalculator();
        var timeFactorCalculator = new TimeFactorCalculator(dateTimeService);

        var factorAggregator = new LinearFactorAggregator(nameFactorCalculator, balanceFactorCalculator, timeFactorCalculator);
        return new DefaultScoreCalculator(factorAggregator);
    }
}
