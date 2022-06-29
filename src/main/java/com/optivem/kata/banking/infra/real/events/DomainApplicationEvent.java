package com.optivem.kata.banking.infra.real.events;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

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
