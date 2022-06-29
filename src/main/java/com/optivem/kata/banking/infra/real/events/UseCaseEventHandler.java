package com.optivem.kata.banking.infra.real.events;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.UseCaseEvent;
import com.optivem.kata.banking.infra.real.events.EventQueue;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * EventListener class that will observe any event of type UseCaseEvent and act accordingly
 * It will also insert the event into queue.
 */
@Component
public class UseCaseEventHandler{

    private final EventQueue<UseCaseEvent> queue;

    public UseCaseEventHandler(EventQueue<UseCaseEvent> eventEventQueue){
        this.queue = eventEventQueue;
    }

    @EventListener
    public void handleEvent(UseCaseEvent event){
            queue.addEvent(event);

            // var eventFromQueue = queue.next();
            // System.out.println(eventFromQueue.getId());
            // System.out.println(eventFromQueue.getEventName());
            // System.out.println(eventFromQueue.getLocalDateTime());

    }

}
