package com.optivem.kata.banking.core.internal.crud.common.guards;

public class IntGuard extends BaseGuard<Integer> {
    public IntGuard(int value) {
        super(value);
    }

    public int againstNegative(String message) {
        return against(this::isNegative, message);
    }

    private boolean isNegative() {
        return value < 0;
    }
}
