package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

public class Balance {
    private int value;

    public Balance(int value) {
        Guard.againstNegative(value, ValidationMessages.BALANCE_NEGATIVE);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Balance add(TransactionAmount amount) {
        return new Balance(value + amount.getValue());
    }

    public Balance subtract(TransactionAmount amount) {
        return new Balance(value - amount.getValue());
    }
}
