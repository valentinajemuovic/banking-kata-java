package com.optivem.kata.banking.core.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.cleanarch.domain.accounts.BankAccount;

public interface ScoreCalculator {
    Score calculate(BankAccount bankAccount);
}
