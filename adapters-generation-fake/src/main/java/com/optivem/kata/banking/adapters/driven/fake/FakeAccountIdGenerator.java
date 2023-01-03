package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.fake.internal.FakeGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;

public class FakeAccountIdGenerator extends FakeGenerator<Long> implements AccountIdGenerator {
}
