package com.optivem.kata.banking.core.internal.crud.common;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.guards.LongGuard;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.guards.ObjectGuard;
import com.optivem.kata.banking.core.internal.crud.common.guards.IntGuard;
import com.optivem.kata.banking.core.internal.crud.common.guards.StringGuard;

public class Guard {
    public static LongGuard guard(long value) {
        return new LongGuard(value);
    }

    public static ObjectGuard guard(Object value) { return new ObjectGuard(value); }

    public static StringGuard guard(String value) {
        return new StringGuard(value);
    }

    public static IntGuard guard(int value) {
        return new IntGuard(value);
    }
}
