package com.optivem.kata.banking.core.internal.crud.common.guards;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.Text;

public class StringGuard extends BaseGuard<String> {

    public StringGuard(String value) {
        super(value);
    }

    public String againstNullOrWhitespace(String message) {
        return against(this::isNullOrWhitespace, message);
    }

    private boolean isNullOrWhitespace() {
        return value == null || value.trim().equals("");
    }
}
