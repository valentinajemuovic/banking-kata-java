package com.optivem.kata.banking.infra.fake.base;

import com.optivem.kata.banking.core.domain.base.Generator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public abstract class BaseFakeGenerator<T> implements Generator<T> {
    private Queue<String> queue;

    protected BaseFakeGenerator() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public T next() {
        if(queue.isEmpty()) {
            throw new FakeException(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
        }

        return (T)queue.remove();
    }

    public void add(String... values) {
        Arrays.stream(values).forEach(e -> queue.add(e));
    }
}
