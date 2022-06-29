package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

import java.util.List;

/**
 * Event Publisher interface will provide eventpublishing functionality to usecases
 */
public interface EventPublisher {
    public void publishEvent(UseCaseEvent event);
}
