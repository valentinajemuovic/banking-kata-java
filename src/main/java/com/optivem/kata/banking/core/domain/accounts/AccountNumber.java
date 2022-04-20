package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

public record AccountNumber(String value) {
    public AccountNumber {
        Guard.againstNullOrWhitespace(value, ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }
}