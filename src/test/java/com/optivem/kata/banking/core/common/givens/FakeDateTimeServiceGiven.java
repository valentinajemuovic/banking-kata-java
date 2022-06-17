package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.time.FakeDateTimeService;

import java.time.LocalDate;
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
