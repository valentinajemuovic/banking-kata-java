package com.optivem.kata.banking.adapters.driven.fake.internal;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.base.Generator;

import java.util.ArrayDeque;
import java.util.Queue;

public class FakeGenerator<T> implements Generator<T> {
    private final Queue<T> queue;

    public FakeGenerator() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public T next() {
        if (queue.isEmpty()) {
            throw new NextElementIsNotSetupException();
        }

        return queue.remove();
    }

    public void add(T value) {
        queue.add(value);
    }
}
