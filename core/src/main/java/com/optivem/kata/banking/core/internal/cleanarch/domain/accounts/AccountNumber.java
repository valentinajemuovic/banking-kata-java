package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;

public record AccountNumber(Text value) {

    public AccountNumber {
        Guard.guard(value).againstNullOrWhitespace(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    public static AccountNumber of(String value) {
        return new AccountNumber(Text.of(value));
    }

    @Override
    public String toString() {
        return value.value();
    }
}