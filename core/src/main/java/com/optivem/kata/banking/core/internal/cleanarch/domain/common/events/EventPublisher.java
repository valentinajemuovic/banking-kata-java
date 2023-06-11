package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.DomainEvent;

public interface EventPublisher {
    void publishEvent(DomainEvent event);
}
