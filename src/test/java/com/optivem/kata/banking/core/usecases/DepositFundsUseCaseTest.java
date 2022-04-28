package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsResponse;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsUseCase;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatRepository;
import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatUseCase;
import static com.optivem.kata.banking.core.common.builders.requests.DepositFundsRequestBuilder.aDepositFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NON_POSITIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;
import static com.optivem.kata.banking.core.common.givens.Givens.givenThatRepository;

class DepositFundsUseCaseTest {

    private FakeBankAccountRepository repository;
    private DepositFundsUseCase useCase;

    @BeforeEach
    void init() {
        this.repository = new FakeBankAccountRepository();
        this.useCase = new DepositFundsUseCase(repository);
    }

    @Test
    void should_deposit_funds_given_valid_request() {
        var accountNumber = "GB41OMQP68570038161775";
        var initialBalance = 100;
        var depositAmount = 50;
        var expectedFinalBalance = 150;

        givenThatRepository(repository).containsBankAccount(accountNumber, initialBalance);

        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .amount(depositAmount)
                .build();

        var expectedResponse = new DepositFundsResponse();
        expectedResponse.setBalance(expectedFinalBalance);

        assertThatUseCase(useCase).withRequest(request).returnsResponse(expectedResponse);

        assertThatRepository(repository).containsBankAccount(accountNumber, expectedFinalBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = aDepositFundsRequest()
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }

    @ParameterizedTest
    @MethodSource(NON_POSITIVE_INTEGERS)
    void should_throw_exception_given_non_positive_amount(int amount) {
        var request = aDepositFundsRequest()
                .amount(amount)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);
    }
}
