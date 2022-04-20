package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;

public class Givens {
    public static BankAccountRepositoryGiven givenThatRepository(BankAccountRepository repository) {
        return new BankAccountRepositoryGiven(repository);
    }

    public static FakeAccountNumberGeneratorGiven givenThatGenerator(FakeAccountNumberGenerator generator) {
        return new FakeAccountNumberGeneratorGiven(generator);
    }
}
