package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.domain.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AccountNumber {
    private final String value;

    public AccountNumber(String value) {
        Guard.againstNullOrWhitespace(value, ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}