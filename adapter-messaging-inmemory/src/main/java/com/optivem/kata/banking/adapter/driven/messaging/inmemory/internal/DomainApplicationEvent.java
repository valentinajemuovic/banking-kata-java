package com.optivem.kata.banking.adapter.driven.messaging.inmemory.internal;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * This class serves as the Event Object that to be published by ApplicationEventPublisher
 */
@Getter
public class DomainApplicationEvent<T> extends ApplicationEvent {
    private final transient T data;

    public DomainApplicationEvent(T data, T data1) {
        super(data);
        this.data = data1;
    }
}
