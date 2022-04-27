package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.common.guards.IntegerGuard;
import com.optivem.kata.banking.core.domain.common.guards.MoneyGuard;
import com.optivem.kata.banking.core.domain.common.guards.OptionalGuard;
import com.optivem.kata.banking.core.domain.common.guards.StringGuard;

import java.util.Optional;

public class Guard {

    private Guard() {
    }

    public static StringGuard guard(String value) {
        return new StringGuard(value);
    }

    public static IntegerGuard guard(Integer value) {
        return new IntegerGuard(value);
    }

    public static <V> OptionalGuard guard(Optional<V> value) {
        return new OptionalGuard(value);
    }

    public static MoneyGuard guard(Money value) {
        return new MoneyGuard(value);
    }
}