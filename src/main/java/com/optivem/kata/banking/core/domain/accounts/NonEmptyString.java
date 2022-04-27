package com.optivem.kata.banking.core.domain.accounts;

import lombok.EqualsAndHashCode;

import static com.optivem.kata.banking.core.domain.common.Guard.guard;

@EqualsAndHashCode
class NonEmptyString {
    private final String value;

    public NonEmptyString(String value, String message) {
        guard(value).againstNullOrWhitespace(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
