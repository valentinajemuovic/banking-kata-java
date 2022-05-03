package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public class NameFactorCalculator implements FactorCalculator {
    @Override
    public int calculate(BankAccount bankAccount) {
        return 5;
    }
}
