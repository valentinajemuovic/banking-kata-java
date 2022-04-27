package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.accounts.Money;
import com.optivem.kata.banking.core.domain.common.Validation;

public class MoneyGuard extends BaseGuard<Money> {
    public MoneyGuard(Money value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(Validation::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(Validation::isNonPositive, message);
    }
}
