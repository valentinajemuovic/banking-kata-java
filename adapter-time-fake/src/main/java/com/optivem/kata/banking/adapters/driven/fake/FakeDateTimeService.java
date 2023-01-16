package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.DateTimeService;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;

public class FakeDateTimeService implements DateTimeService {

    private final Queue<LocalDateTime> queue;

    public FakeDateTimeService() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public LocalDateTime now() {
        if (queue.isEmpty()) {
            throw new NextDateTimeIsNotConfiguredException();
        }

        return queue.remove();
    }

    public void givenNow(LocalDateTime value) {
        queue.add(value);
    }
}