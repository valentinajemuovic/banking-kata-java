package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsRequest;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsResponse;
import com.optivem.kata.banking.core.usecases.withdrawfunds.WithdrawFundsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WithdrawFundsUseCaseTest {
    private WithdrawFundsUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new WithdrawFundsUseCase();
    }

    @Test
    void nothing() {
        var request = new WithdrawFundsRequest();
        var expectedResponse = new WithdrawFundsResponse();

        var response = useCase.handle(request);

        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}
