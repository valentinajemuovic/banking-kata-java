package com.optivem.kata.banking.core.domain.accounts.scoring;

import com.optivem.kata.banking.core.domain.accounts.BankAccount;

public interface ScoreCalculator {
    Score calculate(BankAccount bankAccount);
}
