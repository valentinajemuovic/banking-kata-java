package com.optivem.kata.banking.core.common.factories;

import com.optivem.kata.banking.adapters.driven.fake.*;
import com.optivem.kata.banking.adapters.driven.fake.givens.FakeNationalIdentityProviderGivens;
import com.optivem.kata.banking.adapters.driven.fake.givens.FakeTimeGivens;
import com.optivem.kata.banking.core.Facade;

import java.time.LocalDateTime;

public class FacadeFactory {

    private static final long ACCOUNT_ID_1 = 35236234;
    private static final long ACCOUNT_ID_2 = 63242343;

    private static final String ACCOUNT_NUMBER_1 = "GB41OMQP68570038161775";
    private static final String ACCOUNT_NUMBER_2 = "GB41OMQP68570038161776";

    private static final String NATIONAL_IDENTITY_NUMBER_1 = "XYZ";
    private static final String NATIONAL_IDENTITY_NUMBER_2 = "ABC_1002_B";

    private static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2022, 3, 28, 10, 50);
    private static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2022, 4, 15, 9, 1);

    public Facade create() {
        var nationalIdentityProvider = new FakeNationalIdentityProvider();
        FakeNationalIdentityProviderGivens.givenThat(nationalIdentityProvider).contains(NATIONAL_IDENTITY_NUMBER_1);
        FakeNationalIdentityProviderGivens.givenThat(nationalIdentityProvider).contains(NATIONAL_IDENTITY_NUMBER_2);

        var customerProvider = new FakeCustomerProvider();

        var accountIdGenerator = new FakeAccountIdGenerator();
        accountIdGenerator.givenNext(ACCOUNT_ID_1);
        accountIdGenerator.givenNext(ACCOUNT_ID_2);

        var accountNumberGenerator = new FakeAccountNumberGenerator();
        accountNumberGenerator.givenNext(ACCOUNT_NUMBER_1);
        accountNumberGenerator.givenNext(ACCOUNT_NUMBER_2);

        var dateTimeService = new FakeDateTimeService();
        FakeTimeGivens.givenThat(dateTimeService).willReturn(DATE_TIME_1);
        FakeTimeGivens.givenThat(dateTimeService).willReturn(DATE_TIME_2);

        var bankAccountStorage = new FakeBankAccountStorage();

        var eventBus = new FakeEventBus();

        return new Facade(nationalIdentityProvider, customerProvider, accountIdGenerator, accountNumberGenerator, dateTimeService, bankAccountStorage, eventBus);
    }
}
