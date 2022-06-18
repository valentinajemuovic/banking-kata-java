package com.optivem.kata.banking.core.common.verifies;

import an.awesome.pipelinr.Command;

public class UseCaseVerify<R extends Command<P>, P> {

    private final Command.Handler<R, P> useCase;

    public UseCaseVerify(Command.Handler<R, P> useCase) {
        this.useCase = useCase;
    }

    public UseCaseRequestVerify<R, P> withRequest(R request) {
        return new UseCaseRequestVerify<>(useCase, request);
    }
}
