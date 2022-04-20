package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsResponse;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.builders.requests.DepositFundsRequestBuilder.aDepositFundsRequest;
import static com.optivem.kata.banking.core.common.Assertions.assertResponse;
import static com.optivem.kata.banking.core.common.Assertions.assertThrowsValidationException;
import static com.optivem.kata.banking.core.common.MethodSources.NULL_EMPTY_WHITESPACE;

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

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .build();

        assertThrows(request, ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }


    private void assertSuccess(DepositFundsRequest request, DepositFundsResponse expectedResponse) {
        assertResponse(useCase, request, expectedResponse);
    }

    private void assertThrows(DepositFundsRequest request, String message) {
        assertThrowsValidationException(useCase, request, message);
    }

}
