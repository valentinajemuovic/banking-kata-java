package com.optivem.kata.banking.adapters.driven.fake.verifies;

import com.optivem.kata.banking.adapters.driven.fake.FakeEventBus;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeEventBusVerify {
    private final FakeEventBus eventBus;

    public FakeEventBusVerify(FakeEventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void shouldHavePublishedExactly(Object expectedEvent) {
        var publishedEvents = eventBus.getPublishedEvents();
        assertThat(publishedEvents.size()).isEqualTo(1);
        assertThat(publishedEvents.stream().findFirst().get()).isEqualTo(expectedEvent);
    }
}
