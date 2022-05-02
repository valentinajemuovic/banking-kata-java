package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.common.Verifications;
import com.optivem.kata.banking.core.usecases.UseCase;

import static org.assertj.core.api.Assertions.assertThat;

public class UseCaseRequestVerify<R, P> {

    private final UseCase<R, P> useCase;
    private final R request;

    public UseCaseRequestVerify(UseCase<R, P> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void returnsResponse(P expectedResponse) {
        var response = useCase.handle(request);
        assertThat(response).isEqualTo(expectedResponse);
    }

    public void throwsValidationException(String message) {
        Verifications.verifyThat(() -> useCase.handle(request)).throwsValidationException(message);
    }
}
