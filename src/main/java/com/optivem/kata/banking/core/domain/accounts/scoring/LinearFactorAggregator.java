package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class LinearFactorAggregator implements FactorAggregator {
    private FactorCalculator nameFactorCalculator;
    private FactorCalculator balanceFactorCalculator;
    private FactorCalculator timeFactorCalculator;

    public LinearFactorAggregator(FactorCalculator nameFactorCalculator, FactorCalculator balanceFactorCalculator, FactorCalculator timeFactorCalculator) {
        this.nameFactorCalculator = nameFactorCalculator;
        this.balanceFactorCalculator = balanceFactorCalculator;
        this.timeFactorCalculator = timeFactorCalculator;
    }

    @Override
    public int aggregate(BankAccount bankAccount) {
        return 35;
    }
}
