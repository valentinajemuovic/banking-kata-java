package com.optivem.kata.banking.core.common.assertions;

import com.optivem.kata.banking.core.usecases.VoidUseCase;

public class VoidUseCaseAssert<R> {
    private final VoidUseCase<R> useCase;

    public VoidUseCaseAssert(VoidUseCase<R> useCase) {
        this.useCase = useCase;
    }

    public VoidUseCaseRequestAssert<R> withRequest(R request) {
        return new VoidUseCaseRequestAssert<>(useCase, request);
    }
}
