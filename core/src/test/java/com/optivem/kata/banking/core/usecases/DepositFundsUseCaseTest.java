package com.optivem.kata.banking.core.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.adapter.driven.generation.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapter.driven.generation.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.adapter.driven.messaging.fake.FakeEventBus;
import com.optivem.kata.banking.adapter.driven.persistence.fake.FakeBankAccountStorage;
import com.optivem.kata.banking.adapter.driven.time.fake.FakeDateTimeService;
import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.common.factories.CleanArchUseCaseFactory;
import com.optivem.kata.banking.core.internal.cleanarch.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.internal.cleanarch.usecases.DepositFundsUseCase;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import com.optivem.kata.banking.core.ports.driven.events.FundsDepositedDto;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.DepositFundsRequestBuilder.depositFundsRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NON_POSITIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class DepositFundsUseCaseTest {

    private FakeBankAccountStorage storage;
    private FakeDateTimeService dateTimeService;
    private FakeEventBus eventBus;
    private Command.Handler<DepositFundsRequest, VoidResponse> useCase;

    private static Stream<Arguments> should_deposit_funds_given_valid_request() {
        return Stream.of(Arguments.of("GB41OMQP68570038161775", 0, 50, 50),
                Arguments.of("GB41OMQP68570038161776", 100, 50, 150));
    }

    @BeforeEach
    void init() {
        this.storage = new FakeBankAccountStorage();
        var accountIdGenerator = new FakeAccountIdGenerator();
        var accountNumberGenerator = new FakeAccountNumberGenerator();
        this.dateTimeService = new FakeDateTimeService();
        this.eventBus = new FakeEventBus();

        var useCaseFactory = new CleanArchUseCaseFactory();
        this.useCase = useCaseFactory.createDepositFundsHandler(storage, accountIdGenerator, accountNumberGenerator, dateTimeService, eventBus);
    }

    @ParameterizedTest
    @MethodSource
    void should_deposit_funds_given_valid_request(String accountNumber, int initialBalance, int depositAmount, int expectedFinalBalance) {

        var timestamp = LocalDateTime.of(LocalDate.of(2023, 5, 31), LocalTime.MIN);

        dateTimeService.setupNow(timestamp);

        var existingAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(initialBalance)
                .build();

        storage.givenExists(existingAccount);

        var request = depositFundsRequest()
                .withAccountNumber(accountNumber)
                .withAmount(depositAmount)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .shouldReturnVoidResponse();

        var expectedBankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withBalance(expectedFinalBalance)
                .build();

        storage.shouldContain(expectedBankAccount);

        var expectedEvent = FundsDepositedDto.builder()
                .timestamp(timestamp)
                .accountNumber(accountNumber)
                .amount(depositAmount)
                .build();

        eventBus.shouldHavePublishedExactly(expectedEvent);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = depositFundsRequest()
                .withAccountNumber(accountNumber)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = depositFundsRequest()
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }

    @ParameterizedTest
    @MethodSource(NON_POSITIVE_INTEGERS)
    void should_throw_exception_given_non_positive_amount(int amount) {
        var request = depositFundsRequest()
                .withAmount(amount)
                .build();

        verifyThat(useCase)
                .withRequest(request)
                .shouldThrowValidationException(ValidationMessages.AMOUNT_NOT_POSITIVE);
    }
}
