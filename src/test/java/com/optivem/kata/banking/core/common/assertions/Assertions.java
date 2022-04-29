package com.optivem.kata.banking.core.common.assertions;

import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.usecases.UseCase;
import com.optivem.kata.banking.core.usecases.VoidUseCase;
import org.junit.jupiter.api.function.Executable;

public class Assertions {

    public static ExecutableAssert assertThatExecutable(Executable executable) {
        return new ExecutableAssert(executable);
    }

    public static <R, P> UseCaseAssert<R, P> assertThatUseCase(UseCase<R, P> useCase) {
        return new UseCaseAssert<>(useCase);
    }

    public static <R> VoidUseCaseAssert<R> assertThatUseCase(VoidUseCase<R> useCase) {
        return new VoidUseCaseAssert<>(useCase);
    }

    public static BankAccountRepositoryAssert assertThatRepository(BankAccountRepository repository) {
        return new BankAccountRepositoryAssert(repository);
    }

    public static AccountNumberGeneratorAssert assertThatGenerator(AccountNumberGenerator generator) {
        return new AccountNumberGeneratorAssert(generator);
    }
}
