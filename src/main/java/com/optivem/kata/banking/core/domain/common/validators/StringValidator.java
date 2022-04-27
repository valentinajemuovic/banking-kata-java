package com.optivem.kata.banking.core.domain.common.validators;

public class StringValidator extends BaseValidator<String> {
    public StringValidator(String value) {
        super(value);
    }

    public boolean isNullOrWhitespace() {
        return value == null || value.trim().equals("");
    }
}
