package com.optivem.kata.banking.infra.fake.generators;

import com.optivem.kata.banking.core.domain.generators.AccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FakeAccountNumberGenerator implements AccountNumberGenerator {
    private Queue<String> queue;

    public FakeAccountNumberGenerator() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public String next() {
        if(queue.size() > 0) {
            return queue.remove();
        }

        throw new FakeException(FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT);
    }

    public void add(String... values) {
        Arrays.stream(values).forEach(e -> queue.add(e));
    }
}
