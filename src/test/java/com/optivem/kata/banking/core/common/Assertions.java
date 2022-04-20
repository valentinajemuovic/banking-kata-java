package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.usecases.UseCase;
import org.junit.jupiter.api.function.Executable;

public class Assertions {

    public static ExecutableAssert assertThatExecutable(Executable executable) {
        return new ExecutableAssert(executable);
    }

    public static <R, P> UseCaseAssert<R, P> assertThatUseCase(UseCase<R, P> useCase) {
        return new UseCaseAssert<>(useCase);
    }

    public static BankAccountRepositoryAssert assertThatRepository(BankAccountRepository repository) {
        return new BankAccountRepositoryAssert(repository);
    }
}
