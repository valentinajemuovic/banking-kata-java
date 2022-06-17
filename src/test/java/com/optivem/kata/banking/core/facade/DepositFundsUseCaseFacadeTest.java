package com.optivem.kata.banking.core.facade;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.factories.FacadeFactory;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.DepositFundsRequestBuilder.aDepositFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

public class DepositFundsUseCaseFacadeTest {
    private Facade facade;

    private static Stream<Arguments> should_deposit_funds_given_valid_request() {
        return Stream.of(Arguments.of(0, 50, 50),
                Arguments.of(100, 50, 150));
    }

    @BeforeEach
    void init() {
        var facadeFactory = new FacadeFactory();
        facade = facadeFactory.create();
    }

    @ParameterizedTest
    @MethodSource
    void should_deposit_funds_given_valid_request(int initialBalance, int depositAmount, int expectedFinalBalance) {
        var accountNumber = givenThat(facade)
                .alreadyHasBankAccount(initialBalance);

        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .amount(depositAmount)
                .build();

        verifyThat(facade)
                .withRequest(request)
                .shouldExecuteSuccessfully();

        verifyThat(facade)
                .shouldContain(accountNumber, expectedFinalBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = aDepositFundsRequest()
                .accountNumber(accountNumber)
                .build();

        verifyThat(facade)
                .withRequest(request)
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }
}
