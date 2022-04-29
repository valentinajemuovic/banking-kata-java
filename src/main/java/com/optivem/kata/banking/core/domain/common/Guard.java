package com.optivem.kata.banking.core.domain.common;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.accounts.Text;
import com.optivem.kata.banking.core.domain.common.guards.MoneyGuard;
import com.optivem.kata.banking.core.domain.common.guards.TextGuard;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Guard {

    public static TextGuard guard(Text value) {
        return new TextGuard(value);
    }

    public static MoneyGuard guard(Money value) {
        return new MoneyGuard(value);
    }
}