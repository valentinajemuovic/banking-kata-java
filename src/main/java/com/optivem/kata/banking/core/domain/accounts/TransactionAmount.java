package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public record TransactionAmount(Money value) {

    public TransactionAmount {
        guard(value).againstNonPositive(ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.getValue());
    }
}
