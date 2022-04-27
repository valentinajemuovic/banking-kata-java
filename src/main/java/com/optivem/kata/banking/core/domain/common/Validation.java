package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;

import java.util.Optional;

public class Validation {

    private Validation() {}

    public static boolean isNullOrWhitespace(String value) {
        return value == null || value.trim().equals("");
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }

    public static boolean isNonPositive(int value) {
        return value <= 0;
    }

    public static boolean isNegative(Money value) {
        return value.isNegative();
    }

    public static boolean isNonPositive(Money value) {
        return value.isNonPositive();
    }

    public static <T> boolean isEmpty(Optional<T> value) { return value.isEmpty(); }
}
