package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.adapters.driven.fake.base.FakeGenerator;

public class FakeAccountIdGenerator extends FakeGenerator<Long> implements AccountIdGenerator {
}
