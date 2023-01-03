package com.optivem.kata.banking.adapters.driven.fake.givens;

import com.optivem.kata.banking.adapters.driven.fake.FakeDateTimeService;

public class FakeTimeGivens {
    public static FakeDateTimeServiceGiven givenThat(FakeDateTimeService service) {
        return new FakeDateTimeServiceGiven(service);
    }
}
