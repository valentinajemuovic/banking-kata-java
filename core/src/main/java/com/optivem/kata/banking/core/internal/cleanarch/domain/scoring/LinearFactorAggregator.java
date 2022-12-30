package com.optivem.kata.banking.core.internal.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;

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
        var nameFactor = nameFactorCalculator.calculate(bankAccount);
        var balanceFactor = balanceFactorCalculator.calculate(bankAccount);
        var timeFactor = timeFactorCalculator.calculate(bankAccount);

        return nameFactor + balanceFactor - timeFactor;
    }
}
