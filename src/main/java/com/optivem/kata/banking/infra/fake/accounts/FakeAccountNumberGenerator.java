package com.optivem.kata.banking.infra.fake.accounts;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.AccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.base.BaseFakeGenerator;

public class FakeAccountNumberGenerator extends BaseFakeGenerator<AccountNumber> implements AccountNumberGenerator {
}
