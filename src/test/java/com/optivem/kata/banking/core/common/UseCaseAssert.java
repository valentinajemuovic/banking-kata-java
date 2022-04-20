package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.usecases.UseCase;

import static org.assertj.core.api.Assertions.assertThat;

public class UseCaseAssert<R, P> {

    private final UseCase<R, P> useCase;

    public UseCaseAssert(UseCase<R, P> useCase) {
        this.useCase = useCase;
    }

    public void assertResponse(R request, P expectedResponse) {
        var response = useCase.handle(request);
        assertThat(response).isEqualTo(expectedResponse);
    }
}
