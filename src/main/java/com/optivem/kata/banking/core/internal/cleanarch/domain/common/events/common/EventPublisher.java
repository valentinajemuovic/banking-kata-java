package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common;

public interface EventPublisher {
    void publishEvent(DomainEvent event);
}
