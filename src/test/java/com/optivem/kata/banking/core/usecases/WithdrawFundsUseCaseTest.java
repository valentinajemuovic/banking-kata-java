package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsRequest;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsResponse;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.Assertions.assertThrowsValidationException;
import static com.optivem.kata.banking.core.common.MethodSources.NULL_EMPTY_WHITESPACE;
import static org.assertj.core.api.Assertions.assertThat;

public class WithdrawFundsUseCaseTest {
    private WithdrawFundsUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new WithdrawFundsUseCase();
    }

    @Test
    void nothing() {
        var accountNumber = "GB10BARC20040184197751";

        var request = new WithdrawFundsRequest();
        request.setAccountNumber(accountNumber);

        var expectedResponse = new WithdrawFundsResponse();

        var response = useCase.handle(request);

        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = new WithdrawFundsRequest();
        request.setAccountNumber(accountNumber);

        assertThrows(request, ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    private void assertThrows(WithdrawFundsRequest request, String message) {
        assertThrowsValidationException(useCase, request, message);
    }

}
