package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard.guard;

public record Balance(Money value) {

    public Balance {
        guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
    }

    public Balance add(TransactionAmount amount) {
        return of(value.add(amount.value()));
    }

    public Balance subtract(TransactionAmount amount) {
        return of(value.subtract(amount.value()));
    }

    public static Balance of(Money value) {
        return new Balance(value);
    }

    public static Balance of(int value) {
        return new Balance(Money.of(value));
    }

    public int toInt() {
        return value().value();
    }
}
