package com.optivem.kata.banking.adapter.driven.base;

import com.optivem.kata.banking.core.ports.driven.CustomerGateway;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class CustomerGatewayTest<T extends CustomerGateway> {

    protected static String BLACKLISTED_ID = "ABC_1001";
    protected static String WHITELISTED_ID = "ABC_1002";
    protected static String NON_EXISTENT_ID = "DEF_1002";

    protected T provider;

    @Test
    public void should_return_true_when_user_is_blacklisted() {
        var nationalIdentityNumber = BLACKLISTED_ID;
        setupUserBlacklisted(nationalIdentityNumber);
        var isBlacklisted = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(isBlacklisted).isTrue();
    }

    @Test
    public void should_return_true_when_user_is_whitelisted() {
        var nationalIdentityNumber = WHITELISTED_ID;
        setupUserWhitelisted(nationalIdentityNumber);
        var isBlacklisted = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(isBlacklisted).isFalse();
    }

    @Test
    public void should_return_false_when_customer_not_exists() {
        var nationalIdentityNumber = NON_EXISTENT_ID;
        var exists = provider.isBlacklisted(nationalIdentityNumber);
        assertThat(exists).isFalse();
    }

    protected void setupUserBlacklisted(String nationalIdentityNumber) {
        return;
    }

    protected void setupUserWhitelisted(String nationalIdentityNumber) {
        return;
    }
}
