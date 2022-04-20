package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.usecases.UseCase;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Assertions {

    public static void assertThrowsRepositoryException(Executable executable, String message) {
        var exception = assertThrows(RepositoryException.class, executable);
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    public static void assertThrowsValidationException(Executable executable, String message) {
        var exception = assertThrows(ValidationException.class, executable);
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    public static <R, P, U extends UseCase<R, P>> void assertThrowsValidationException(U useCase, R request, String message) {
        assertThrowsValidationException(() -> useCase.handle(request), message);
    }

    public static <R, P> UseCaseAssert assertThatUseCase(UseCase<R, P> useCase) {
        return new UseCaseAssert(useCase);
    }

    public static BankAccountRepositoryAssert assertThatRepository(BankAccountRepository repository) {
        return new BankAccountRepositoryAssert(repository);
    }
}
