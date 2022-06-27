package com.optivem.kata.banking.core.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccount;

public class BalanceFactorCalculator implements FactorCalculator {
    private static final int INCREMENT = 5;

    @Override
    public int calculate(BankAccount bankAccount) {
        var balance = bankAccount.getBalance().toInt();
        return balance + INCREMENT;
    }
}
