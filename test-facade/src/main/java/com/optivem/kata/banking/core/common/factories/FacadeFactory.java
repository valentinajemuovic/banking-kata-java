package com.optivem.kata.banking.core.common.factories;

import com.optivem.kata.banking.adapter.driven.generation.fake.FakeAccountIdGenerator;
import com.optivem.kata.banking.adapter.driven.generation.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.adapter.driven.messaging.fake.FakeEventBus;
import com.optivem.kata.banking.adapter.driven.microservice.fake.FakeCustomerGateway;
import com.optivem.kata.banking.adapter.driven.persistence.fake.FakeBankAccountStorage;
import com.optivem.kata.banking.adapter.driven.thirdparty.fake.FakeNationalIdentityGateway;
import com.optivem.kata.banking.adapter.driven.time.fake.FakeDateTimeService;
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
        var nationalIdentityProvider = new FakeNationalIdentityGateway();
        nationalIdentityProvider.setupExists(NATIONAL_IDENTITY_NUMBER_1);
        nationalIdentityProvider.setupExists(NATIONAL_IDENTITY_NUMBER_2);

        var customerProvider = new FakeCustomerGateway();

        var accountIdGenerator = new FakeAccountIdGenerator();
        accountIdGenerator.setupNext(ACCOUNT_ID_1);
        accountIdGenerator.setupNext(ACCOUNT_ID_2);

        var accountNumberGenerator = new FakeAccountNumberGenerator();
        accountNumberGenerator.setupNext(ACCOUNT_NUMBER_1);
        accountNumberGenerator.setupNext(ACCOUNT_NUMBER_2);

        var dateTimeService = new FakeDateTimeService();
        dateTimeService.setupNow(DATE_TIME_1);
        dateTimeService.setupNow(DATE_TIME_2);

        var bankAccountStorage = new FakeBankAccountStorage();

        var eventBus = new FakeEventBus();

        return new Facade(nationalIdentityProvider, customerProvider, accountIdGenerator, accountNumberGenerator, dateTimeService, bankAccountStorage, eventBus);
    }
}
