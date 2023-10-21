package com.optivem.kata.banking.adapter.time.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SysDateTimeServiceTest {
    private static final int SLEEP = 1;

    private SysDateTimeService service;

    @BeforeEach
    public void init() {
        this.service = new SysDateTimeService();
    }

    @Test
    void should_return_current_date_time() {
        var dateTime = service.now();
        assertThat(dateTime).isNotNull();
    }

    @Test
    void should_return_different_date_times() throws InterruptedException {
        var dateTime1 = service.now();
        assertThat(dateTime1).isNotNull();

        Thread.sleep(SLEEP);

        var dateTime2 = service.now();
        assertThat(dateTime2)
                .isNotNull()
                .isAfter(dateTime1);

        Thread.sleep(SLEEP);

        var dateTime3 = service.now();
        assertThat(dateTime3)
                .isNotNull()
                .isAfter(dateTime2);
    }
}
