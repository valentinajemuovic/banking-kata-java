package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public interface FactorCalculator {
    int calculate(BankAccount bankAccount);
}
