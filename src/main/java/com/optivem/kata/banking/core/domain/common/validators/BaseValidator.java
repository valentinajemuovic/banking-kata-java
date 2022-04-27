package com.optivem.kata.banking.core.domain.common.validators;

public class BaseValidator<T> {

    protected final T value;

    public BaseValidator(T value) {
        this.value = value;
    }
}
