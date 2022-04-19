package com.optivem.kata.banking.infra.fake.base;

import com.optivem.kata.banking.core.domain.base.Generator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public abstract class BaseFakeGenerator<T> implements Generator<T> {
    private final Queue<T> queue;

    protected BaseFakeGenerator() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public T next() {
        if(queue.isEmpty()) {
            throw new FakeException(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
        }

        return queue.remove();
    }

    public void add(T... values) {
        queue.addAll(List.of(values));
    }
}
