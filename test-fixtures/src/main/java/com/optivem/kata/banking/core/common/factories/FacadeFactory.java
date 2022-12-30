package com.optivem.kata.banking.core.common.factories;

import com.optivem.kata.banking.adapters.driven.fake.*;
import com.optivem.kata.banking.core.Facade;

import java.time.LocalDateTime;

import static com.optivem.kata.banking.core.common.Givens.givenThat;

public class FacadeFactory {

    private static final long ACCOUNT_ID_1 = 35236234;
    private static final long ACCOUNT_ID_2 = 63242343;

    private static final String ACCOUNT_NUMBER_1 = "GB41OMQP68570038161775";
    private static final String ACCOUNT_NUMBER_2 = "GB41OMQP68570038161776";

    private static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2022, 3, 28, 10, 50);
    private static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2022, 4, 15, 9, 1);

    public Facade create() {
        var accountIdGenerator = new FakeAccountIdGenerator();
        givenThat(accountIdGenerator).willGenerate(ACCOUNT_ID_1);
        givenThat(accountIdGenerator).willGenerate(ACCOUNT_ID_2);

        var accountNumberGenerator = new FakeAccountNumberGenerator();
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_1);
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_2);

        var dateTimeService = new FakeDateTimeService();
        givenThat(dateTimeService).willReturn(DATE_TIME_1);
        givenThat(dateTimeService).willReturn(DATE_TIME_2);

        var bankAccountStorage = new FakeBankAccountStorage();

        var eventBus = new FakeEventBus();

        return new Facade(accountIdGenerator, accountNumberGenerator, dateTimeService, bankAccountStorage, eventBus);
    }
}
