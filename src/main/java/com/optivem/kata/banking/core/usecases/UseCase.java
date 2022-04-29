package com.optivem.kata.banking.core.usecases;

public interface UseCase<R, P> {
    P handle(R request);
}