package com.optivem.kata.banking.core.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.EventPublisher;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.UseCaseEvent;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.internal.cleanarch.usecases.OpenAccountUseCase;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.infra.fake.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.openAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NEGATIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;
import static org.assertj.core.api.Assertions.assertThat;

class OpenAccountUseCaseTest {
    private FakeBankAccountStorage storage;
    private FakeAccountIdGenerator accountIdGenerator;
    private FakeAccountNumberGenerator accountNumberGenerator;
    private FakeDateTimeService dateTimeService;

    private FakeEventPublisher eventPublisher;

    private Command.Handler<OpenAccountRequest, OpenAccountResponse> useCase;



    private static Stream<Arguments> should_open_account_given_valid_request() {
        return Stream.of(Arguments.of("John", "Smith", 0, 3456, "GB41OMQP68570038161775", LocalDate.of(2022, 5, 20)),
                Arguments.of("Mary", "McDonald", 50, 735353, "GB36BMFK75394735916876", LocalDate.of(2021, 6, 15)));
    }

    @BeforeEach
    void init() {
        this.storage = new FakeBankAccountStorage();
        this.accountIdGenerator = new FakeAccountIdGenerator();
        this.accountNumberGenerator = new FakeAccountNumberGenerator();
        this.dateTimeService = new FakeDateTimeService();
        this.eventPublisher = new FakeEventPublisher();
        this.useCase = createCleanArchHandler();
        // this.useCase = createCrudHandler();

        // TODO: VC: Make configurable so that we can run same test twice
    }

    private Command.Handler<OpenAccountRequest, OpenAccountResponse> createCleanArchHandler() {
        var repository = new BankAccountRepositoryImpl(storage, accountIdGenerator, accountNumberGenerator);
        this.dateTimeService = new FakeDateTimeService();
        return new OpenAccountUseCase(repository, dateTimeService, eventPublisher);
    }

    private Command.Handler<OpenAccountRequest, OpenAccountResponse> createCrudHandler() {
        return new com.optivem.kata.banking.core.internal.crud.OpenAccountUseCase(storage, accountIdGenerator, accountNumberGenerator, dateTimeService, eventPublisher);
    }

    @ParameterizedTest
    @MethodSource
    void should_open_account_given_valid_request(String firstName, String lastName, int initialBalance, long generatedAccountId, String generatedAccountNumber, LocalDate openingDate) {
        givenThat(accountIdGenerator).willGenerate(generatedAccountId);
        givenThat(accountNumberGenerator).willGenerate(generatedAccountNumber);
        givenThat(dateTimeService).willReturn(LocalDateTime.of(openingDate, LocalTime.MIN));

        var request = openAccountRequest()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(initialBalance)
                .build();

        var expectedResponse = new OpenAccountResponse();
        expectedResponse.setAccountNumber(generatedAccountNumber);

        verifyThat(useCase).withRequest(request).shouldReturnResponse(expectedResponse);

        verifyThat(storage).shouldContain(generatedAccountId, generatedAccountNumber, firstName, lastName, openingDate, initialBalance);

        var expectedPublishedEvents = List.of(new UseCaseEvent(AccountId.of(generatedAccountId), "AccountOpened"));

        var publishedEvents = eventPublisher.getPublishedEvents();

        assertThat(publishedEvents.stream().findFirst().get().getEventName()).isEqualTo(expectedPublishedEvents.stream().findFirst().get().getEventName());
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_first_name(String firstName) {
        var request = openAccountRequest()
                .withFirstName(firstName)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.FIRST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_last_name(String lastName) {
        var request = openAccountRequest()
                .withLastName(lastName)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.LAST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NEGATIVE_INTEGERS)
    void should_throw_exception_given_negative_balance(int balance) {
        var request = openAccountRequest()
                .withBalance(balance)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.BALANCE_NEGATIVE);
    }
}
