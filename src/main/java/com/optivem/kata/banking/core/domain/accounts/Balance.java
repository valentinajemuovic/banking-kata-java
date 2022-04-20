package com.optivem.kata.banking.core.domain.accounts;

public class Balance {
    private int value;

    public Balance(int value) {
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
