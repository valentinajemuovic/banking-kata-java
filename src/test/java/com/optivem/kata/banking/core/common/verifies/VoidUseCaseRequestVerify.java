package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.usecases.VoidUseCase;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;

public class VoidUseCaseRequestVerify<R> {
    private final VoidUseCase<R> useCase;
    private final R request;

    public VoidUseCaseRequestVerify(VoidUseCase<R> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void shouldExecuteSuccessfully() {
        useCase.handle(request);
    }

    public void shouldThrowValidationException(String message) {
        verifyThat(() -> useCase.handle(request)).shouldThrowValidationException(message);
    }
}
