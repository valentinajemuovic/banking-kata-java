package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.usecases.VoidUseCase;

public class VoidUseCaseVerify<R> {
    private final VoidUseCase<R> useCase;

    public VoidUseCaseVerify(VoidUseCase<R> useCase) {
        this.useCase = useCase;
    }

    public VoidUseCaseRequestVerify<R> withRequest(R request) {
        return new VoidUseCaseRequestVerify<>(useCase, request);
    }
}
