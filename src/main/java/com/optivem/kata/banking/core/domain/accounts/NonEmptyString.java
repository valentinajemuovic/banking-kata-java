package com.optivem.kata.banking.core.domain.accounts;

import com.optivem.kata.banking.core.common.Guard;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class NonEmptyString {
    private String value;

    public NonEmptyString(String value, String message) {
        Guard.againstNullOrWhitespace(value, message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
