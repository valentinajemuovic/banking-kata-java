package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

public interface EventPublisher {
    void publishEvent(DomainEvent event);
}
