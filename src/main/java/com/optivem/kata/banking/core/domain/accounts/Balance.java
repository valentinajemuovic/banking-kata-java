package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public record Balance(Money value) {

    public Balance {
        guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
    }

    public Balance add(TransactionAmount amount) {
        return new Balance(value.add(amount.value()));
    }

    public Balance subtract(TransactionAmount amount) {
        return new Balance(value.subtract(amount.value()));
    }
}
