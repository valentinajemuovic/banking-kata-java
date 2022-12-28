package com.optivem.kata.banking.core.ports.driven.events;

import java.time.LocalDateTime;

public interface EventDto {
    LocalDateTime getTimestamp();
}
