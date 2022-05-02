package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.usecases.UseCase;

public class UseCaseVerify<R, P> {

    private final UseCase<R, P> useCase;

    public UseCaseVerify(UseCase<R, P> useCase) {
        this.useCase = useCase;
    }

    public UseCaseRequestVerify<R, P> withRequest(R request) {
        return new UseCaseRequestVerify<>(useCase, request);
    }
}
