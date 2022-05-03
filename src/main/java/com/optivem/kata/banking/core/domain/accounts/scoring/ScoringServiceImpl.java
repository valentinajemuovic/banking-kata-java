package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class ScoringServiceImpl implements ScoringService {

    private FactorAggregator factorAggregator;

    public ScoringServiceImpl(FactorAggregator factorAggregator) {
        this.factorAggregator = factorAggregator;
    }

    @Override
    public Score calculateScore(BankAccount bankAccount) {
        return Score.A;
    }
}
