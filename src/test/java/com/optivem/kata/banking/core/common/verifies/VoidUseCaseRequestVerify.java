package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.common.Verifications;
import com.optivem.kata.banking.core.usecases.VoidUseCase;

public class VoidUseCaseRequestVerify<R> {
    private final VoidUseCase<R> useCase;
    private final R request;

    public VoidUseCaseRequestVerify(VoidUseCase<R> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void executeSuccessfully() {
        useCase.handle(request);
    }

    public void throwsValidationException(String message) {
        Verifications.verifyThat(() -> useCase.handle(request)).throwsValidationException(message);
    }
}
