package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TransactionAmount {
    private int value;

    public TransactionAmount(int value) {
        Guard.againstNonPositive(value, ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean greaterThan(Balance balance) {
        return value > balance.getValue();
    }
}
