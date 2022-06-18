package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.time.DateTimeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SysDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime getCurrent() {
        return LocalDateTime.now();
    }
}
