package com.optivem.kata.banking.core.internal.crud.common.guards;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.ValidationException;

import java.util.function.BooleanSupplier;

public class BaseGuard<T> {

    protected final T value;

    public BaseGuard(T value) {
        this.value = value;
    }

    protected T against(BooleanSupplier tester, String message) {
        if (tester.getAsBoolean()) {
            throw new ValidationException(message);
        }

        return value;
    }

    public void againstNull(String message) {
        if (value == null) {
            throw new ValidationException(message);
        }
    }
}
