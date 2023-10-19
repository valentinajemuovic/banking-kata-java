package com.optivem.kata.banking.core.internal.cleanarch.domain.common.guards;

public class LongGuard extends BaseGuard<Long> {
    public LongGuard(long value) {
        super(value);
    }

    public void againstNonPositive(String message) {
        against(this::isNonPositive, message);
    }

    private boolean isNonPositive() {
        return value <= 0;
    }
}
