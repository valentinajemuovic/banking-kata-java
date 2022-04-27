package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

public record AccountNumber(Text value) {

    public AccountNumber {
        guard(value).againstNullOrWhitespace(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    public static AccountNumber of(String value) {
        return new AccountNumber(Text.of(value));
    }
}