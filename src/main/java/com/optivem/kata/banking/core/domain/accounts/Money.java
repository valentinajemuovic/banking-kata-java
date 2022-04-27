package com.optivem.kata.banking.core.domain.accounts;

public record Money(int value) {

    public static final Money ZERO = new Money(0);

    public Money add(Money other) {
        return new Money(value + other.value());
    }

    public Money subtract(Money other) {
        return new Money(value - other.value());
    }

    public boolean greaterThan(Money other) {
        return value > other.value();
    }

    public boolean lessThan(Money other) {
        return value < other.value();
    }

    public boolean lessThanOrEqualTo(Money other) {
        return value <= other.value();
    }

    public boolean isNegative() {
        return lessThan(ZERO);
    }

    public boolean isNonPositive() {
        return lessThanOrEqualTo(ZERO);
    }

    public static Money of(int value) {
        return new Money(value);
    }
}
