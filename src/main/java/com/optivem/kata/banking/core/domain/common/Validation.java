package com.optivem.kata.banking.core.domain.common;

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

    public static <T> boolean isEmpty(Optional<T> value) { return value.isEmpty(); }
}
