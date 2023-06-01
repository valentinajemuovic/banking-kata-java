package com.optivem.kata.banking.adapter.driven.messaging.fake;

import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeEventBus implements EventBus {
    private final Queue<EventDto> events;

    public FakeEventBus() {
        this.events = new ArrayDeque<>();
    }

    @Override
    public void publish(EventDto event) {
        events.add(event);
    }

    public void shouldHavePublishedExactly(EventDto expectedEvent) {
        var publishedEvents = getPublishedEvents();
        assertThat(publishedEvents.size()).isEqualTo(1);
        assertThat(publishedEvents.stream().findFirst())
                .isPresent()
                .contains(expectedEvent);
    }

    private List<EventDto> getPublishedEvents() {
        return events.stream().toList();
    }
}
