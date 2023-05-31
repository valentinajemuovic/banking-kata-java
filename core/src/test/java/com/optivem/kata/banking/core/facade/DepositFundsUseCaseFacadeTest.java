package com.optivem.kata.banking.core.facade;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.factories.FacadeFactory;
import com.optivem.kata.banking.core.facade.common.TestFacade;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.DepositFundsRequestBuilder.depositFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

/**
 * This test is an alternative approach to DepositFundsUseCaseTest, so we would use either of these, but not both
 */

// TODO: VC: DELETE

    /*
class DepositFundsUseCaseFacadeTest {
    private TestFacade facade;

    private static Stream<Arguments> should_deposit_funds_given_valid_request() {
        return Stream.of(Arguments.of(0, 50, 50),
                Arguments.of(100, 50, 150));
    }

    @BeforeEach
    void init() {
        var facadeFactory = new FacadeFactory();
        facade = new TestFacade(facadeFactory.create());
    }

    @ParameterizedTest
    @MethodSource
    void should_deposit_funds_given_valid_request(int initialBalance, int depositAmount, int expectedFinalBalance) {

        var timestamp = LocalDateTime.of(LocalDate.of(2023, 5, 31), LocalTime.MIN);

        facade.willHaveNextTime(timestamp);

        var accountNumber = facade
                .alreadyHasBankAccount(initialBalance);

        var request = depositFundsRequest()
                .withAccountNumber(accountNumber)
                .withAmount(depositAmount)
                .build();

        verifyThat(facade.getFacade())
                .withRequest(request)
                .shouldExecuteSuccessfully();

        verifyThat(facade.getFacade())
                .shouldContain(accountNumber, expectedFinalBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = depositFundsRequest()
                .withAccountNumber(accountNumber)
                .build();

        verifyThat(facade.getFacade())
                .withRequest(request)
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }
}
*/