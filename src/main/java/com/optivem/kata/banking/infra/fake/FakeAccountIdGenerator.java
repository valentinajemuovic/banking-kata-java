package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.infra.fake.base.FakeGenerator;

public class FakeAccountIdGenerator extends FakeGenerator<Long> implements AccountIdGenerator {
}
