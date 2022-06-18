package com.optivem.kata.banking.core.common.factories;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.infra.fake.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.FakeBankAccountRepository;
import com.optivem.kata.banking.infra.fake.FakeDateTimeService;

import java.time.LocalDateTime;

import static com.optivem.kata.banking.core.common.Givens.givenThat;

public class FacadeFactory {

    private static final String ACCOUNT_NUMBER_1 = "GB41OMQP68570038161775";
    private static final String ACCOUNT_NUMBER_2 = "GB41OMQP68570038161776";

    private static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2022, 3, 28, 10, 50);
    private static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2022, 4, 15, 9, 1);


    public Facade create() {
        var accountNumberGenerator = new FakeAccountNumberGenerator();
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_1);
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_2);

        var dateTimeService = new FakeDateTimeService();
        givenThat(dateTimeService).willReturn(DATE_TIME_1);
        givenThat(dateTimeService).willReturn(DATE_TIME_2);

        var bankAccountRepository = new FakeBankAccountRepository();

        return new Facade(accountNumberGenerator, dateTimeService, bankAccountRepository);
    }
}
