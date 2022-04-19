package com.optivem.kata.banking.core.domain.base;

public interface Generator<T> {
    T next();
}
