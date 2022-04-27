package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.common.Validation;

public class IntegerGuard extends BaseGuard<Integer> {
    public IntegerGuard(Integer value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(Validation::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(Validation::isNonPositive, message);
    }
}
