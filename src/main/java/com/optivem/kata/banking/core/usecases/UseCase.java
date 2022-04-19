package com.optivem.kata.banking.core.usecases;

public interface UseCase<R extends Request<P>, P> {
    P handle(R request);
}
