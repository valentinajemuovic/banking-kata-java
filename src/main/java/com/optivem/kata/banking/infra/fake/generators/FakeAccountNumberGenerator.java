package com.optivem.kata.banking.infra.fake.generators;

import com.optivem.kata.banking.core.domain.generators.AccountNumberGenerator;
import com.optivem.kata.banking.infra.fake.exceptions.FakeException;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FakeAccountNumberGenerator extends BaseFakeGenerator<String> implements AccountNumberGenerator {
}
