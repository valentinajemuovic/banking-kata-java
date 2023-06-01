package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.AccountOpened;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.FundsDeposited;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.FundsWithdrawn;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.DomainEvent;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.EventPublisher;
import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {

    private final EventBus eventBus;

    public EventPublisherImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publishEvent(DomainEvent event) {
        var eventDto = getEventDto(event);
        eventBus.publish(eventDto);
    }

    private EventDto getEventDto(DomainEvent event) {
        if(event instanceof AccountOpened)
        {
            return AccountOpenedConverter.fromEvent((AccountOpened) event);
        }
        else if(event instanceof FundsDeposited) {
            return FundsDepositedConverter.fromEvent((FundsDeposited) event);
        }
        else if(event instanceof FundsWithdrawn) {
            return FundsWithdrawnConverter.fromEvent((FundsWithdrawn) event);
        }

        throw new IllegalArgumentException("Unrecognized event type");
    }
}
