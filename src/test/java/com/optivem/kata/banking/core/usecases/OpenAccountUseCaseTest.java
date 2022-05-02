package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
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

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.anOpenAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NEGATIVE_INTEGERS;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;

class OpenAccountUseCaseTest {

    private FakeAccountNumberGenerator accountNumberGenerator;
    private BankAccountRepository bankAccountRepository;
    private OpenAccountUseCase useCase;

    private static Stream<Arguments> should_open_account_given_request_is_valid() {
        return Stream.of(Arguments.of("John", "Smith", 0, "GB41OMQP68570038161775"),
                Arguments.of("Mary", "McDonald", 50, "GB36BMFK75394735916876"));
    }

    @BeforeEach
    void init() {
        this.accountNumberGenerator = new FakeAccountNumberGenerator();
        this.bankAccountRepository = new FakeBankAccountRepository();
        this.useCase = new OpenAccountUseCase(accountNumberGenerator, bankAccountRepository);
    }

    @ParameterizedTest
    @MethodSource
    void should_open_account_given_request_is_valid(String firstName, String lastName, int initialBalance, String accountNumber) {
        givenThat(accountNumberGenerator).willGenerate(accountNumber);

        var request = anOpenAccountRequest()
                .firstName(firstName)
                .lastName(lastName)
                .initialBalance(initialBalance)
                .build();

        var expectedResponse = new OpenAccountResponse();
        expectedResponse.setAccountNumber(accountNumber);

        verifyThat(useCase).withRequest(request).returnsResponse(expectedResponse);

        verifyThat(bankAccountRepository).containsBankAccount(accountNumber, firstName, lastName, initialBalance);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_first_name_is_empty(String firstName) {
        var request = anOpenAccountRequest()
                .firstName(firstName)
                .build();

        verifyThat(useCase).withRequest(request).throwsValidationException(ValidationMessages.FIRST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_last_name_is_empty(String lastName) {
        var request = anOpenAccountRequest()
                .lastName(lastName)
                .build();

        verifyThat(useCase).withRequest(request).throwsValidationException(ValidationMessages.LAST_NAME_EMPTY);
    }

    @ParameterizedTest
    @MethodSource(NEGATIVE_INTEGERS)
    void should_throw_exception_given_initial_balance_is_negative(int initialBalance) {
        var request = anOpenAccountRequest()
                .initialBalance(initialBalance)
                .build();

        verifyThat(useCase).withRequest(request).throwsValidationException(ValidationMessages.BALANCE_NEGATIVE);
    }
}
