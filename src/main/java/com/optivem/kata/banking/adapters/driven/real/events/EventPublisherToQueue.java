package com.optivem.kata.banking.adapters.driven.real.events;


import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class EventPublisherToQueue implements EventBus {

    private ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherToQueue(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void send(EventDto event) {
        applicationEventPublisher.publishEvent(event);
    }
}
