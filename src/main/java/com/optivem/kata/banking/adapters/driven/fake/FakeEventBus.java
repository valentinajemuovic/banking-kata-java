package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class FakeEventBus implements EventBus {

    private Queue<EventDto> events;

    public FakeEventBus() {
        this.events = new ArrayDeque<>();
    }

    public List<EventDto> getPublishedEvents() {
        return events.stream().toList();
    }

    @Override
    public void send(EventDto event) {
        events.add(event);
    }
}
