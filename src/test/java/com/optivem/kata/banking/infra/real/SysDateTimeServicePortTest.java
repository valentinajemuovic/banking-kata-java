package com.optivem.kata.banking.infra.real;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.Givens.givenThat;
import static org.assertj.core.api.Assertions.assertThat;

public class SysDateTimeServicePortTest {
    private static final int SLEEP = 1;

    private SysDateTimeService service;

    @BeforeEach
    private void init() {
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
        assertThat(dateTime2).isNotNull();
        assertThat(dateTime2).isAfter(dateTime1);

        Thread.sleep(SLEEP);

        var dateTime3 = service.now();
        assertThat(dateTime3).isNotNull();
        assertThat(dateTime3).isAfter(dateTime2);
    }
}
