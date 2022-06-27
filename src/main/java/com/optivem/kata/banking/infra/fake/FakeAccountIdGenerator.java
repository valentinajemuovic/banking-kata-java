package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.gateways.AccountIdGenerator;
import com.optivem.kata.banking.infra.fake.base.FakeGenerator;

public class FakeAccountIdGenerator extends FakeGenerator<AccountId> implements AccountIdGenerator {
}
