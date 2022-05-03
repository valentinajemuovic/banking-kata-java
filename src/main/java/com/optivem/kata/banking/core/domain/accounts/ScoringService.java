package com.optivem.kata.banking.core.domain.accounts;

public interface ScoringService {
    Score calculateScore(BankAccount bankAccount);
}
