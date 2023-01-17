package com.optivem.kata.banking.adapter.driven;

import com.optivem.kata.banking.core.ports.driven.CustomerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class CustomerProviderTest<T extends CustomerProvider> {

    protected T provider;

    @BeforeEach
    private void init() {
        this.provider = create();
    }

    protected abstract T create();

    @Test
    void should_return_response() {
        var nationalIdentityNumber = "ABC-10001";
        var isBlacklisted = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(isBlacklisted).isNotNull();
    }
}
