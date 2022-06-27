package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.common.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import com.optivem.kata.banking.infra.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.infra.fake.FakeBankAccountRepository;
import com.optivem.kata.banking.infra.fake.FakeDateTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.openAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NEGATIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class OpenAccountUseCaseTest {
    private FakeAccountIdGenerator accountIdGenerator;
    private FakeAccountNumberGenerator accountNumberGenerator;
    private FakeDateTimeService dateTimeService;
    private BankAccountRepository bankAccountRepository;
    private OpenAccountUseCase useCase;

    private static Stream<Arguments> should_open_account_given_valid_request() {
        return Stream.of(Arguments.of("John", "Smith", 0, 3456, "GB41OMQP68570038161775", LocalDate.of(2022, 5, 20)),
                Arguments.of("Mary", "McDonald", 50, 735353, "GB36BMFK75394735916876", LocalDate.of(2021, 6, 15)));
    }

    @BeforeEach
    void init() {
        this.accountIdGenerator = new FakeAccountIdGenerator();
        this.accountNumberGenerator = new FakeAccountNumberGenerator();
        this.dateTimeService = new FakeDateTimeService();
        this.bankAccountRepository = new FakeBankAccountRepository();
        this.useCase = new OpenAccountUseCase(accountIdGenerator, accountNumberGenerator, dateTimeService, bankAccountRepository);
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

        verifyThat(bankAccountRepository).shouldContain(generatedAccountId, generatedAccountNumber, firstName, lastName, openingDate, initialBalance);
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
