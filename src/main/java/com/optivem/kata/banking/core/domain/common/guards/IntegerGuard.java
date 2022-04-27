package com.optivem.kata.banking.core.domain.common.guards;

import static com.optivem.kata.banking.core.domain.common.Validation.validate;

public class IntegerGuard extends BaseGuard<Integer> {
    public IntegerGuard(Integer value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(validate(value)::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(validate(value)::isNonPositive, message);
    }
}
