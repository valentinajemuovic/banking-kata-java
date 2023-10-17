package com.optivem.kata.banking.core.common.http;

import org.springframework.http.HttpMethod;

public final class HttpMethodName {
    private HttpMethodName() throws InstantiationException {
        throw new InstantiationException("Should not create an instance of this class");
    }

    public static final String GET = HttpMethod.GET.name();
}
