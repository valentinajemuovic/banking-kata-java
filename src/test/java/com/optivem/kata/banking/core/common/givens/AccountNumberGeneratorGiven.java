package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.infra.fake.accounts.FakeAccountNumberGenerator;

public class AccountNumberGeneratorGiven {

    private FakeAccountNumberGenerator generator;

    public AccountNumberGeneratorGiven(FakeAccountNumberGenerator generator) {
        this.generator = generator;
    }

    public void willGenerate(String number) {
        var accountNumber = new AccountNumber(number);
        generator.add(accountNumber);
    }
}
