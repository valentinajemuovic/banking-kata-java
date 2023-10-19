package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountOpened;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.FundsDeposited;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.FundsWithdrawn;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.DomainEvent;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.EventPublisher;
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
        if(event instanceof AccountOpened accountOpened) {
            return AccountOpenedConverter.fromEvent(accountOpened);
        }
        else if(event instanceof FundsDeposited fundsDeposited) {
            return FundsDepositedConverter.fromEvent(fundsDeposited);
        }
        else if(event instanceof FundsWithdrawn fundsWithdrawn) {
            return FundsWithdrawnConverter.fromEvent(fundsWithdrawn);
        }

        throw new IllegalArgumentException("Unrecognized event type");
    }
}
