package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.gateways.AccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.base.FakeGenerator;

public class FakeAccountNumberGenerator extends FakeGenerator<AccountNumber> implements AccountNumberGenerator {
}
