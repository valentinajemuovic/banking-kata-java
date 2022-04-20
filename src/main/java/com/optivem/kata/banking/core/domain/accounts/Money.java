package com.optivem.kata.banking.core.domain.accounts;

public record Money(int value) {

    public Money add(Money amount) {
        return new Money(value + amount.value());
    }

    public Money subtract(Money amount) {
        return new Money(value - amount.value());
    }

    public boolean greaterThan(Money balance) {
        return value > balance.value();
    }
}
