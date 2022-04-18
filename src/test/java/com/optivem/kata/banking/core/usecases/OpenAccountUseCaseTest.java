package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenAccountUseCaseTest {

    private OpenAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new OpenAccountUseCase();
    }

    @Test
    void nothing() {
        var request = new OpenAccountRequest();
        var expectedResponse = new OpenAccountResponse();

        var response = useCase.handle(request);

        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}
