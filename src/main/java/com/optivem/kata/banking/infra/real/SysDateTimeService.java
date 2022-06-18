package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.time.DateTimeService;

import java.time.LocalDateTime;

public class SysDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime getCurrent() {
        return LocalDateTime.now();
    }
}
