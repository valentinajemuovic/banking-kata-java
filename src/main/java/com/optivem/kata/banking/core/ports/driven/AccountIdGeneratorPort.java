package com.optivem.kata.banking.core.ports.driven;

import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.domain.common.base.Generator;

public interface AccountIdGeneratorPort extends Generator<Long> {
}
