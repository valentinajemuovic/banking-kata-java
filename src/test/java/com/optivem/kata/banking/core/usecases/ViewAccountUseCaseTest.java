package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatUseCase;
import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.aViewAccountRequest;

class ViewAccountUseCaseTest {

    private ViewAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new ViewAccountUseCase();
    }

    @Test
    void nothing() {
        var request = aViewAccountRequest()
                .build();

        var expectedResponse = new ViewAccountResponse();

        assertThatUseCase(useCase).withRequest(request).returnsResponse(expectedResponse);
    }
}
