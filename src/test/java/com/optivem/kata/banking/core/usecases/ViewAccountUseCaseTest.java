package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.common.builders.entities.BankAccountDtoTestBuilder;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.scoring.Score;
import com.optivem.kata.banking.core.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.domain.common.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;
import com.optivem.kata.banking.infra.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.infra.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.FakeBankAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.viewAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ViewAccountUseCaseTest {
    private FakeBankAccountStorage storage;
    private BankAccountRepository repository;
    private ScoreCalculator scoreCalculator;
    private ViewAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.storage = new FakeBankAccountStorage();
        var accountIdGenerator = new FakeAccountIdGenerator();
        var accountNumberGenerator = new FakeAccountNumberGenerator();
        this.repository = new BankAccountRepositoryImpl(storage, accountIdGenerator, accountNumberGenerator);
        this.scoreCalculator = mock(ScoreCalculator.class);
        this.useCase = new ViewAccountUseCase(repository, scoreCalculator);
    }

    @Test
    void should_view_account_given_valid_request() {
        var accountNumber = "3223fsfds";
        var firstName = "Kelly";
        var lastName = "McDonald";
        var initialBalance = 400;
        var fullName = "Kelly McDonald";
        var score = Score.A;

        givenThat(storage).alreadyHasBankAccount(accountNumber, firstName, lastName, initialBalance);

        var bankAccount = BankAccountDtoTestBuilder.bankAccount()
                .withAccountNumber(accountNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBalance(initialBalance)
                .buildEntity();

        given(scoreCalculator.calculate(bankAccount)).willReturn(score);

        var request = viewAccountRequest()
                .withAccountNumber(accountNumber)
                .build();

        var expectedResponse = new ViewAccountResponse();
        expectedResponse.setAccountNumber("3223fsfds");
        expectedResponse.setFullName(fullName);
        expectedResponse.setBalance(initialBalance);
        expectedResponse.setScore(score);

        verifyThat(useCase).withRequest(request).shouldReturnResponse(expectedResponse);
    }

    @ParameterizedTest
    @MethodSource(NULL_EMPTY_WHITESPACE)
    void should_throw_exception_given_empty_account_number(String accountNumber) {
        var request = viewAccountRequest()
                .withAccountNumber(accountNumber)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = viewAccountRequest()
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }
}
