package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.usecases.UseCase;

import static com.optivem.kata.banking.core.common.Assertions.assertThatExecutable;
import static org.assertj.core.api.Assertions.assertThat;

public class UseCaseRequestAssert<R, P> {

    private final UseCase<R, P> useCase;
    private final R request;

    public UseCaseRequestAssert(UseCase<R, P> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void assertResponse(P expectedResponse) {
        var response = useCase.handle(request);
        assertThat(response).isEqualTo(expectedResponse);
    }

    public void throwsValidationException(String message) {
        assertThatExecutable(() -> useCase.handle(request)).throwsValidationException(message);
    }
}
