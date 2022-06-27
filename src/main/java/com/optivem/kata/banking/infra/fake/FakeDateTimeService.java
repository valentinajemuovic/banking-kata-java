package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.ports.driven.DateTimeServicePort;
import com.optivem.kata.banking.infra.fake.base.FakeGenerator;

import java.time.LocalDateTime;

public class FakeDateTimeService implements DateTimeServicePort {

    private final FakeGenerator<LocalDateTime> dateGenerator;

    public FakeDateTimeService() {
        this.dateGenerator = new FakeGenerator<>();
    }

    @Override
    public LocalDateTime now() {
        return dateGenerator.next();
    }

    public void add(LocalDateTime dateTime) {
        dateGenerator.add(dateTime);
    }
}
