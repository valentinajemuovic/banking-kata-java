package com.optivem.kata.banking.adapters.driven.real.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * This class serves as the Event Object that to be puslished by ApplicationEventPublisher
 */
@Getter
public class DomainApplicationEvent<T> extends ApplicationEvent {
    private T data;

    public DomainApplicationEvent(T data) {
        super(data);
    }
}
