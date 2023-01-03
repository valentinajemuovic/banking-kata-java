package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.fake.base.FakeGenerator;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;

import java.time.LocalDateTime;

public class FakeDateTimeService implements DateTimeService {

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
