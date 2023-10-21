package com.optivem.kata.banking.adapter.driven.time.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDateTimeServiceTest {
    private FakeDateTimeService service;

    @BeforeEach
    public void init() {
        this.service = new FakeDateTimeService();
    }

    @Test
    void should_return_current_date_time() {
        var dateTime = LocalDateTime.of(2022, 4, 15, 9, 1);
        service.setupNow(dateTime);

        assertReturnsNext(dateTime);
    }

    private void assertReturnsNext(LocalDateTime dateTime) {
        var current = service.now();
        assertThat(current).isEqualTo(dateTime);
    }
}
