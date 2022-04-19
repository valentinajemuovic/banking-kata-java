package com.optivem.kata.banking.core.common;

public class Validation {

    public static boolean isNullOrWhitespace(String value) {
        return value == null || value.trim().equals("");
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }

    public static boolean isNonPositive(int value) {
        return value <= 0;
    }
}
