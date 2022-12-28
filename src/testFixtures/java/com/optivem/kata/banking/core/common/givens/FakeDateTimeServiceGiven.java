package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeDateTimeService;

import java.time.LocalDateTime;

public class FakeDateTimeServiceGiven {
    private final FakeDateTimeService service;

    public FakeDateTimeServiceGiven(FakeDateTimeService service) {
        this.service = service;
    }

    public void willReturn(LocalDateTime dateTime) {
        service.add(dateTime);
    }
}
