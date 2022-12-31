package com.optivem.kata.banking.adapters.time.system;

import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SysDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
