package com.optivem.kata.banking.adapter.driven.messaging.inmemory.internal;

import org.apache.logging.log4j.message.Message;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * EventListener class that will observe any event of type UseCaseEvent and act accordingly
 * It will also insert the event into queue.
 */
@Component
public class UseCaseEventHandler{

    private final EventQueue<DomainApplicationEvent<Message>> queue;

    public UseCaseEventHandler(EventQueue<DomainApplicationEvent<Message>> eventEventQueue){
        this.queue = eventEventQueue;
    }

    @EventListener
    public void handleEvent(DomainApplicationEvent<Message> event){
            queue.addEvent(event);
    }
}
