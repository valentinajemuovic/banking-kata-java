package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.EventPublisher;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.UseCaseEvent;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class FakeEventPublisher implements EventPublisher {

    private Queue<UseCaseEvent> events;

    public FakeEventPublisher() {
        this.events = new ArrayDeque<>();
    }

    @Override
    public void publishEvent(UseCaseEvent event) {
        events.add(event);
    }


    public List<UseCaseEvent> getPublishedEvents() {
        return events.stream().toList();
    }
}
