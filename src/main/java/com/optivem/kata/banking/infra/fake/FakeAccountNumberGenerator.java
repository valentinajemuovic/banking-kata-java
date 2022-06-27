package com.optivem.kata.banking.infra.fake;

import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.base.FakeGenerator;

public class FakeAccountNumberGenerator extends FakeGenerator<String> implements AccountNumberGenerator {
}
