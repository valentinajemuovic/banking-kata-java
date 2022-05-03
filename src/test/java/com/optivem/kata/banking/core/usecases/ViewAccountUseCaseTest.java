package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.scoring.Score;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.aViewAccountRequest;
import static com.optivem.kata.banking.core.common.data.MethodSources.NULL_EMPTY_WHITESPACE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ViewAccountUseCaseTest {

    private BankAccountRepository repository;
    private ScoreCalculator scoreCalculator;
    private ViewAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.repository = new FakeBankAccountRepository();
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

        givenThat(repository).alreadyHasBankAccount(accountNumber, firstName, lastName, initialBalance);

        var bankAccount = BankAccountBuilder.aBankAccount()
                .accountNumber(accountNumber)
                .firstName(firstName)
                .lastName(lastName)
                .balance(initialBalance)
                .build();

        given(scoreCalculator.calculate(bankAccount)).willReturn(score);

        var request = aViewAccountRequest()
                .accountNumber(accountNumber)
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
        var request = aViewAccountRequest()
                .accountNumber(accountNumber)
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_non_existent_account_number() {
        var request = aViewAccountRequest()
                .build();

        verifyThat(useCase).withRequest(request).shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
    }
}
