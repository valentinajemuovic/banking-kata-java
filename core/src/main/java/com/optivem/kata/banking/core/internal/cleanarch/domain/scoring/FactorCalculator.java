package com.optivem.kata.banking.core.internal.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;

public interface FactorCalculator {
    int calculate(BankAccount bankAccount);
}
