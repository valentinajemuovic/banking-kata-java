package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BaseDomainEvent implements DomainEvent {
    private LocalDateTime timestamp;
}
