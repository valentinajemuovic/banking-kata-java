package com.optivem.kata.banking.core.ports.driven;

import com.optivem.kata.banking.core.ports.driven.events.EventDto;

public interface EventBus {
    void send(EventDto event);
}
