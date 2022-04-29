package com.optivem.kata.banking.core.usecases;

public interface VoidUseCase<R> {
    void handle(R request);
}
