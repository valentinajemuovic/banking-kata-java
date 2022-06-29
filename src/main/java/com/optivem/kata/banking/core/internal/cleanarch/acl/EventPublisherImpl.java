package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.AccountOpened;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.DomainEvent;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.EventPublisher;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.stereotype.Component;

import javax.transaction.NotSupportedException;

@Component
public class EventPublisherImpl implements EventPublisher {

    private final EventBus eventBus;

    public EventPublisherImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publishEvent(DomainEvent event) {
        var eventDto = getEventDto(event);
        eventBus.send(eventDto);
    }

    private EventDto getEventDto(DomainEvent event) {
        if(event instanceof AccountOpened)
        {
            return AccountOpenedConverter.fromEvent((AccountOpened) event);
        }

        return null;
    }
}
