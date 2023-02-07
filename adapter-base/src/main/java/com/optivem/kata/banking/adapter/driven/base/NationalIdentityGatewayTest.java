package com.optivem.kata.banking.adapter.driven.base;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityGateway;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class NationalIdentityGatewayTest<T extends NationalIdentityGateway> {

    public static String EXISTING_ID = "2";
    public static String INVALID_ID = "99";

    protected T provider;

    @Test
    public void should_return_true_when_user_exists() {
        var nationalIdentityNumber = EXISTING_ID;
        setupExistentUser(nationalIdentityNumber);
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isTrue();
    }

    @Test
    public void should_return_false_when_user_not_exists() {
        var nationalIdentityNumber = INVALID_ID;
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isFalse();
    }

    protected void setupExistentUser(String nationalIdentityNumber) {

    }
}
