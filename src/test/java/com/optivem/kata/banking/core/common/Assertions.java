package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.usecases.Request;
import com.optivem.kata.banking.core.usecases.UseCase;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Assertions {
    public static <R extends Request<P>, P, U extends UseCase<R, P>> void assertThrowsValidationException(U useCase, R request, String message) {
        var exception = assertThrows(ValidationException.class, () -> useCase.handle(request));
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
