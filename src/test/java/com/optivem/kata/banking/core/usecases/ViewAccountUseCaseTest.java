package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatUseCase;
import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.aViewAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;
import static com.optivem.kata.banking.core.common.givens.Givens.givenThatRepository;

class ViewAccountUseCaseTest {

    private FakeBankAccountRepository repository;
    private ViewAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.repository = new FakeBankAccountRepository();
        this.useCase = new ViewAccountUseCase(repository);
    }

    @Test
    void should_view_account_given_valid_request() {
        var accountNumber = "3223fsfds";
        var firstName = "Kelly";
        var lastName = "McDonald";
        var initialBalance = 400;
        var fullName = "Kelly McDonald";

        givenThatRepository(repository).alreadyHasBankAccount(accountNumber, firstName, lastName, initialBalance);

        var request = aViewAccountRequest()
                .accountNumber(accountNumber)
                .build();

        var expectedResponse = new ViewAccountResponse();
        expectedResponse.setAccountNumber("3223fsfds");
        expectedResponse.setFullName(fullName);
        expectedResponse.setBalance(initialBalance);

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

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = aViewAccountRequest()
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }
}
