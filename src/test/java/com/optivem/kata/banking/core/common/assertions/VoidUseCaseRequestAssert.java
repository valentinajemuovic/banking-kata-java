package com.optivem.kata.banking.core.common.assertions;

import com.optivem.kata.banking.core.usecases.VoidUseCase;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatExecutable;

public class VoidUseCaseRequestAssert<R> {
    private final VoidUseCase<R> useCase;
    private final R request;

    public VoidUseCaseRequestAssert(VoidUseCase<R> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void executeSuccessfully() {
        useCase.handle(request);
    }

    public void throwsValidationException(String message) {
        assertThatExecutable(() -> useCase.handle(request)).throwsValidationException(message);
    }
}
