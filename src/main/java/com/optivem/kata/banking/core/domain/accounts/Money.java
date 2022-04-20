package com.optivem.kata.banking.core.domain.accounts;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money add(Money amount) {
        return new Money(value + amount.getValue());
    }

    public Money subtract(Money amount) {
        return new Money(value - amount.getValue());
    }

    public boolean greaterThan(Money balance) {
        return value > balance.getValue();
    }
}
