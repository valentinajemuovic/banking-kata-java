package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class EventPublisherToQueue implements EventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherToQueue(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(UseCaseEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
