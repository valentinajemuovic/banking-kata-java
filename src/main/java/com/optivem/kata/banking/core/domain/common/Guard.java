package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.common.guards.IntegerGuard;
import com.optivem.kata.banking.core.domain.common.guards.OptionalGuard;
import com.optivem.kata.banking.core.domain.common.guards.StringGuard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;

import java.util.Optional;
import java.util.function.Predicate;

public class Guard {

    private Guard() {}

    public static StringGuard guard(String value) {
        return new StringGuard(value);
    }

    public static IntegerGuard guard(Integer value) {
        return new IntegerGuard(value);
    }

    public static <V> OptionalGuard guard(Optional<V> value) {
        return new OptionalGuard(value);
    }

    // TODO: VC: Pending delete after refactoring out all the guards
    public static <T> void against(Predicate<T> tester, T value, String message) {
        if(tester.test(value)) {
            throw new ValidationException(message);
        }
    }

    public static void againstNegative(Money value, String message) {
        guard(value.value()).againstNegative(message);
    }

    public static void againstNonPositive(Money value, String message) {
        guard(value.value()).againstNonPositive(message);
    }


}