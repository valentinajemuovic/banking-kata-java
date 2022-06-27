package com.optivem.kata.banking.core.cleanarch.domain.common.events;

import com.optivem.kata.banking.core.cleanarch.domain.accounts.AccountId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * This class serves as the Event Object that to be puslished by ApplicationEventPublisher
 */
@Getter
public class UseCaseEvent extends ApplicationEvent {

    private AccountId id;
    private String eventName;
    private LocalDateTime localDateTime;
    public UseCaseEvent(AccountId id, String eventName) {
        super(id);
        this.id = id;
        this.eventName = eventName;
        this.localDateTime = LocalDateTime.now();
    }
}
