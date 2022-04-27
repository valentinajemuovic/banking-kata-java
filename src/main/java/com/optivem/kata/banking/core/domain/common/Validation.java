package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.common.validators.IntegerValidator;
import com.optivem.kata.banking.core.domain.common.validators.StringValidator;

import java.util.Optional;

public class Validation {

    private Validation() {}

    public static StringValidator validate(String value) {
        return new StringValidator(value);
    }

    public static IntegerValidator validate(int value) {
        return new IntegerValidator(value);
    }

    public static boolean isNegative(Money value) {
        return value.isNegative();
    }

    public static boolean isNonPositive(Money value) {
        return value.isNonPositive();
    }

    public static <T> boolean isEmpty(Optional<T> value) { return value.isEmpty(); }
}
