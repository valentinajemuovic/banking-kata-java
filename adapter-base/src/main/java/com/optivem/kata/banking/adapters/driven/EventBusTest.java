package com.optivem.kata.banking.adapters.driven;

import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class EventBusTest<T extends EventBus> {

    protected T eventBus;

    @BeforeEach
    private void init() {
        this.eventBus = create();
    }

    protected abstract T create();

    @Test
    void should_execute_without_error() {
        var eventDto = new AccountOpenedDto();
        eventBus.publish(eventDto);
    }
}
