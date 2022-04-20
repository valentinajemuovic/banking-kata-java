package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatRepository;
import static com.optivem.kata.banking.core.common.assertions.Assertions.assertThatUseCase;
import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.anOpenAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NEGATIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class OpenAccountUseCaseTest {

    private FakeAccountNumberGenerator accountNumberGenerator;
    private FakeBankAccountRepository bankAccountRepository;
    private OpenAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.accountNumberGenerator = new FakeAccountNumberGenerator();
        this.bankAccountRepository = new FakeBankAccountRepository();
        this.useCase = new OpenAccountUseCase(accountNumberGenerator, bankAccountRepository);
    }

    @ParameterizedTest
    @MethodSource
    void should_open_account_given_request_is_valid(String firstName, String lastName, int initialBalance, String accountNumber) {
        setupNextRandomAccountNumber(accountNumber);

        var request = anOpenAccountRequest()
                .firstName(firstName)
                .lastName(lastName)
                .initialBalance(initialBalance)
                .build();

        var expectedResponse = new OpenAccountResponse();
        expectedResponse.setAccountNumber(accountNumber);

        assertThatUseCase(useCase).withRequest(request).assertResponse(expectedResponse);

        assertThatRepository(bankAccountRepository).containsBankAccount(accountNumber, firstName, lastName, initialBalance);
    }

    private static Stream<Arguments> should_open_account_given_request_is_valid() {
        return Stream.of(Arguments.of("John", "Smith", 0, "GB41OMQP68570038161775"),
                Arguments.of("Mary", "McDonald", 50, "GB36BMFK75394735916876"));
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_first_name_is_empty(String firstName) {
        var request = anOpenAccountRequest()
                .firstName(firstName)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.FIRST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_last_name_is_empty(String lastName) {
        var request = anOpenAccountRequest()
                .lastName(lastName)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.LAST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NEGATIVE_INTEGERS)
    void should_throw_exception_given_initial_balance_is_negative(int initialBalance) {
        var request = anOpenAccountRequest()
                .initialBalance(initialBalance)
                .build();

        assertThatUseCase(useCase).withRequest(request).throwsValidationException(ValidationMessages.INITIAL_BALANCE_NEGATIVE);
    }

    private void setupNextRandomAccountNumber(String accountNumber) {
        accountNumberGenerator.add(new AccountNumber(accountNumber));
    }
}
