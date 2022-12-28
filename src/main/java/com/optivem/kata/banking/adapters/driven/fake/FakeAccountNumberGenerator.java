package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.fake.base.FakeGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;

public class FakeAccountNumberGenerator extends FakeGenerator<String> implements AccountNumberGenerator {
}
