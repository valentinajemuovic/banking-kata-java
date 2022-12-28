package com.optivem.kata.banking.core.common;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.adapters.driven.fake.FakeEventBus;
import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.verifies.*;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.function.Executable;

public class Verifications {

    public static ExecutableVerify verifyThat(Executable executable) {
        return new ExecutableVerify(executable);
    }

    public static <R extends Command<P>, P> UseCaseVerify<R, P> verifyThat(Command.Handler<R, P> useCase) {
        return new UseCaseVerify<>(useCase);
    }

    public static BankAccountStorageVerify verifyThat(BankAccountStorage storage) {
        return new BankAccountStorageVerify(storage);
    }

    public static FacadeVerify verifyThat(Facade facade) {
        return new FacadeVerify(facade);
    }

    public static FakeEventBusVerify verifyThat(FakeEventBus eventBus) {
        return new FakeEventBusVerify(eventBus);
    }
}
