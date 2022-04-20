package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TransactionAmount {
    private final Money value;

    public TransactionAmount(Money value) {
        Guard.againstNonPositive(value, ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);
        this.value = value;
    }

    public TransactionAmount(int value) {
        this(new Money(value));
    }

    public Money getValue() {
        return value;
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.getValue());
    }
}
