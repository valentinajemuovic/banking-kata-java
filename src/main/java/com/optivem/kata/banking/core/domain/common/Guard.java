package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Guard {

    private Guard() {}

    public static void against(Predicate<String> tester, String value, String message) {
        if(tester.test(value)) {
            throw new ValidationException(message);
        }
    }

    public static void against(IntPredicate tester, int value, String message) {
        if(tester.test(value)) {
            throw new ValidationException(message);
        }
    }

    public static void againstNullOrWhitespace(String value, String message) {
        against(Validation::isNullOrWhitespace, value, message);
    }

    public static void againstNegative(int value, String message) {
        against(Validation::isNegative, value, message);
    }

    public static void againstNegative(Money value, String message) {
        againstNegative(value.getValue(), message);
    }

    public static void againstNonPositive(int value, String message) {
        against(Validation::isNonPositive, value, message);
    }

    public static void againstNonPositive(Money value, String message) {
        againstNonPositive(value.getValue(), message);
    }
}