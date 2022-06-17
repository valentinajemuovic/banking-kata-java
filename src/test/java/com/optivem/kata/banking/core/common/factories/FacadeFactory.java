package com.optivem.kata.banking.core.common.factories;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.accounts.FakeBankAccountRepository;

import static com.optivem.kata.banking.core.common.Givens.givenThat;

public class FacadeFactory {

    private static final String ACCOUNT_NUMBER_1 = "GB41OMQP68570038161775";
    private static final String ACCOUNT_NUMBER_2 = "GB41OMQP68570038161776";

    public Facade create() {
        var accountNumberGenerator = new FakeAccountNumberGenerator();
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_1);
        givenThat(accountNumberGenerator).willGenerate(ACCOUNT_NUMBER_2);

        var bankAccountRepository = new FakeBankAccountRepository();

        return new Facade(accountNumberGenerator, bankAccountRepository);
    }
}
