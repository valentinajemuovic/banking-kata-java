package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.common.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsUseCase;
import com.optivem.kata.banking.infra.fake.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.WithdrawFundsRequestBuilder.withdrawFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NON_POSITIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class WithdrawFundsUseCaseTest {

    private BankAccountRepository repository;
    private WithdrawFundsUseCase useCase;

    private static Stream<Arguments> should_withdraw_funds_given_valid_request() {
        return Stream.of(Arguments.of("GB10BARC20040184197751", 70, 30, 40),
                Arguments.of("GB36BMFK75394735916876", 100, 100, 0));
    }

    @BeforeEach
    void init() {
        this.repository = new FakeBankAccountRepository();
        this.useCase = new WithdrawFundsUseCase(repository);
    }

    @ParameterizedTest
    @MethodSource
    void should_withdraw_funds_given_valid_request(String accountNumber, int initialBalance, int amount, int expectedFinalBalance) {
        givenThat(repository).alreadyHasBankAccount(accountNumber, initialBalance);

        var request = withdrawFundsRequest()
                .withAccountNumber(accountNumber)
                .withAmount(amount)
                .build();

        verifyThat(useCase).withRequest(request).shouldReturnVoidResponse();

        verifyThat(repository).shouldContain(accountNumber, expectedFinalBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = withdrawFundsRequest()
                .withAccountNumber(accountNumber)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NON_POSITIVE_INTEGERS)
    void should_throw_exception_given_non_positive_amount(int amount) {
        var request = withdrawFundsRequest()
                .withAmount(amount)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.AMOUNT_NOT_POSITIVE);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = withdrawFundsRequest()
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }

    @Test
    void should_throw_exception_given_insufficient_funds() {
        var accountNumber = "GB10BARC20040184197751";
        var balance = 140;
        var amount = 141;

        givenThat(repository).alreadyHasBankAccount(accountNumber, balance);

        var request = withdrawFundsRequest()
                .withAccountNumber(accountNumber)
                .withAmount(amount)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.INSUFFICIENT_FUNDS);

        verifyThat(repository).shouldContain(accountNumber, balance);
    }
}
