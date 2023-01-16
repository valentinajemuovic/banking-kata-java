package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.CustomerProviderTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeCustomerProviderTest extends CustomerProviderTest<FakeCustomerProvider> {
    @Override
    protected FakeCustomerProvider create() {
        return new FakeCustomerProvider();
    }

    @Test
    void should_return_blacklisted() {
        var nationalIdentityNumber = "ABC101";
        provider.givenBlacklisted(nationalIdentityNumber);
        var isBlacklisted = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(isBlacklisted).isTrue();
    }

    @Test
    void should_return_whitelisted() {
        var nationalIdentityNumber = "ABC101";
        var isBlacklisted = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(isBlacklisted).isFalse();
    }
}
