package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

public record Money(int value) {

    public static final Money ZERO = Money.of(0);

    public Money add(Money other) {
        return Money.of(value + other.value());
    }

    public Money subtract(Money other) {
        return Money.of(value - other.value());
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
