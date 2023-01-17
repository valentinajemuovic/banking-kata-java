package com.optivem.kata.banking.adapter.messaging.inmemory.internal;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * EventListener class that will observe any event of type UseCaseEvent and act accordingly
 * It will also insert the event into queue.
 */
@Component
public class UseCaseEventHandler{

    private final EventQueue<DomainApplicationEvent> queue;

    public UseCaseEventHandler(EventQueue<DomainApplicationEvent> eventEventQueue){
        this.queue = eventEventQueue;
    }

    @EventListener
    public void handleEvent(DomainApplicationEvent event){
            queue.addEvent(event);

            // var eventFromQueue = queue.next();
            // System.out.println(eventFromQueue.getId());
            // System.out.println(eventFromQueue.getEventName());
            // System.out.println(eventFromQueue.getLocalDateTime());

    }

}
