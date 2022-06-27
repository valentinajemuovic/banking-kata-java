package com.optivem.kata.banking.core.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.cleanarch.domain.common.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.cleanarch.domain.common.Guard.guard;

public record TransactionAmount(Money value) {

    public TransactionAmount {
        guard(value).againstNonPositive(ValidationMessages.AMOUNT_NOT_POSITIVE);
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.value());
    }

    public static TransactionAmount of(Money value) {
        return new TransactionAmount(value);
    }

    public static TransactionAmount of(int value) {
        return of(Money.of(value));
    }
}
