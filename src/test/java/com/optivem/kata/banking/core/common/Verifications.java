package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.common.verifies.*;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.usecases.UseCase;
import com.optivem.kata.banking.core.usecases.VoidUseCase;
import org.junit.jupiter.api.function.Executable;

public class Verifications {

    public static ExecutableVerify verifyThat(Executable executable) {
        return new ExecutableVerify(executable);
    }

    public static <R, P> UseCaseVerify<R, P> verifyThat(UseCase<R, P> useCase) {
        return new UseCaseVerify<>(useCase);
    }

    public static <R> VoidUseCaseVerify<R> verifyThat(VoidUseCase<R> useCase) {
        return new VoidUseCaseVerify<>(useCase);
    }

    public static BankAccountRepositoryVerify verifyThat(BankAccountRepository repository) {
        return new BankAccountRepositoryVerify(repository);
    }

    public static AccountNumberGeneratorVerify verifyThat(AccountNumberGenerator generator) {
        return new AccountNumberGeneratorVerify(generator);
    }
}
