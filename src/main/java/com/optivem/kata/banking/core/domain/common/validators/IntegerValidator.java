package com.optivem.kata.banking.core.domain.common.validators;

public class IntegerValidator extends BaseValidator<Integer> {
    public IntegerValidator(Integer value) {
        super(value);
    }

    public boolean isNegative() {
        return value < 0;
    }

    public boolean isNonPositive() {
        return value <= 0;
    }
}
