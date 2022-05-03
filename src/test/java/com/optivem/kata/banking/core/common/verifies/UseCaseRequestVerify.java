package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.usecases.UseCase;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UseCaseRequestVerify<R, P> {

    private final UseCase<R, P> useCase;
    private final R request;

    public UseCaseRequestVerify(UseCase<R, P> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void shouldReturnResponse(P expectedResponse) {
        var response = useCase.handle(request);
        assertThat(response).isEqualTo(expectedResponse);
    }

    public void shouldThrowValidationException(String message) {
        verifyThat(() -> useCase.handle(request)).shouldThrowValidationException(message);
    }
}
