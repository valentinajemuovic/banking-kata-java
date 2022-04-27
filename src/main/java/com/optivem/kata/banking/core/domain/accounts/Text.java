package com.optivem.kata.banking.core.domain.accounts;

public record Text(String value) {
    public boolean isNullOrWhitespace() {
        return value == null || value.trim().equals("");
    }

    public static Text of(String value) {
        return new Text(value);
    }
}
