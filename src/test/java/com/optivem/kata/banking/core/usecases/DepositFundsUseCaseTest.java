package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsUseCase;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.DepositFundsRequestBuilder.aDepositFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NON_POSITIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class DepositFundsUseCaseTest {

    private BankAccountRepository repository;
    private DepositFundsUseCase useCase;

    private static Stream<Arguments> should_deposit_funds_given_valid_request() {
        return Stream.of(Arguments.of("GB41OMQP68570038161775", 0, 50, 50),
                Arguments.of("GB41OMQP68570038161776", 100, 50, 150));
    }

    @BeforeEach
    void init() {
        this.repository = new FakeBankAccountRepository();
        this.useCase = new DepositFundsUseCase(repository);
    }

    @ParameterizedTest
    @MethodSource
    void should_deposit_funds_given_valid_request(String accountNumber, int initialBalance, int depositAmount, int expectedFinalBalance) {
        givenThat(repository)
                .alreadyHasBankAccount(accountNumber, initialBalance);

        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .amount(depositAmount)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .executeSuccessfully();

        verifyThat(repository)
                .containsBankAccount(accountNumber, expectedFinalBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = aDepositFundsRequest()
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .throwsValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }

    @ParameterizedTest
    @MethodSource(NON_POSITIVE_INTEGERS)
    void should_throw_exception_given_non_positive_amount(int amount) {
        var request = aDepositFundsRequest()
                .amount(amount)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .throwsValidationException(ValidationMessages.NON_POSITIVE_TRANSACTION_AMOUNT);
    }
}
