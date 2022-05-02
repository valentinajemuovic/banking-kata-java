package com.optivem.kata.banking.core.common;

import com.optivem.kata.banking.core.common.givens.BankAccountRepositoryGiven;
import com.optivem.kata.banking.core.common.givens.FakeAccountNumberGeneratorGiven;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;

public class Givens {
    public static BankAccountRepositoryGiven givenThat(BankAccountRepository repository) {
        return new BankAccountRepositoryGiven(repository);
    }

    public static FakeAccountNumberGeneratorGiven givenThat(FakeAccountNumberGenerator generator) {
        return new FakeAccountNumberGeneratorGiven(generator);
    }
}
