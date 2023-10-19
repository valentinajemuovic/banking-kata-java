package com.optivem.kata.banking.core.common.http;

import org.springframework.http.HttpStatus;

public final class HttpStatusValue {
    private HttpStatusValue() {
    }

    public static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();
    public static final int OK = HttpStatus.OK.value();
}
