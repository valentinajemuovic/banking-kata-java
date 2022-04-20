package com.optivem.kata.banking.core.common.assertions;

import com.optivem.kata.banking.core.usecases.UseCase;

public class UseCaseAssert<R, P> {

    private final UseCase<R, P> useCase;

    public UseCaseAssert(UseCase<R, P> useCase) {
        this.useCase = useCase;
    }

    public UseCaseRequestAssert<R, P> withRequest(R request) {
        return new UseCaseRequestAssert<>(useCase, request);
    }
}
