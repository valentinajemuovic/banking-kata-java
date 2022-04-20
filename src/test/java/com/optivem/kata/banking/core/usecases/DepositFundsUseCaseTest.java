package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsResponse;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.builders.requests.DepositFundsRequestBuilder.aDepositFundsRequest;
import static com.optivem.kata.banking.core.common.Assertions.assertResponse;

class DepositFundsUseCaseTest {

    private DepositFundsUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new DepositFundsUseCase();
    }

    @Test
    void should_deposit_funds_given_valid_request() {
        var request = aDepositFundsRequest()
                .build();

        var expectedResponse = new DepositFundsResponse();

        assertSuccess(request, expectedResponse);
    }

    private void assertSuccess(DepositFundsRequest request, DepositFundsResponse expectedResponse) {
        assertResponse(useCase, request, expectedResponse);
    }

}
