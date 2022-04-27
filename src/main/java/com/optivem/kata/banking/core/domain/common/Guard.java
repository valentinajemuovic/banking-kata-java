package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.accounts.Text;
import com.optivem.kata.banking.core.domain.common.guards.MoneyGuard;
import com.optivem.kata.banking.core.domain.common.guards.OptionalGuard;
import com.optivem.kata.banking.core.domain.common.guards.TextGuard;

import java.util.Optional;

public class Guard {

    private Guard() {
    }

    public static TextGuard guard(Text value) {
        return new TextGuard(value);
    }

    public static <V> OptionalGuard<V> guard(Optional<V> value) {
        return new OptionalGuard<>(value);
    }

    public static MoneyGuard guard(Money value) {
        return new MoneyGuard(value);
    }
}