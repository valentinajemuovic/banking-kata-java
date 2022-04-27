package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public class Balance {
    private final Money value;

    private Balance(Money value) {
        guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
        this.value = value;
    }

    public Balance(int value) {
        this(new Money(value));
    }

    public Money getValue() {
        return value;
    }

    public Balance add(TransactionAmount amount) {
        return new Balance(value.add(amount.getValue()));
    }

    public Balance subtract(TransactionAmount amount) {
        return new Balance(value.subtract(amount.getValue()));
    }
}
