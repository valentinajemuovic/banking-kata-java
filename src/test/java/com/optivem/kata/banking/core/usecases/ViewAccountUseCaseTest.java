package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatUseCase;
import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.aViewAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

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

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = aViewAccountRequest()
                .accountNumber(accountNumber)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }
}
