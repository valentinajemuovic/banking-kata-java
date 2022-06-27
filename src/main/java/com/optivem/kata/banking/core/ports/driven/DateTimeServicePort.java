package com.optivem.kata.banking.core.ports.driven;

import java.time.LocalDateTime;

public interface DateTimeServicePort {
    LocalDateTime now();
}
