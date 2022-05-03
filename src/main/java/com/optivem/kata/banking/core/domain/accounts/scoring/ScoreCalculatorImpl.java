package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class ScoreCalculatorImpl implements ScoreCalculator {

    private FactorAggregator factorAggregator;

    public ScoreCalculatorImpl(FactorAggregator factorAggregator) {
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
}
