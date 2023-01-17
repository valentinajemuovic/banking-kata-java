package com.optivem.kata.banking.adapter.messaging.inmemory;


import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class ApplicationEventBus implements EventBus {

    private ApplicationEventPublisher applicationEventPublisher;

    public ApplicationEventBus(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(EventDto event) {
        applicationEventPublisher.publishEvent(event);
    }
}
