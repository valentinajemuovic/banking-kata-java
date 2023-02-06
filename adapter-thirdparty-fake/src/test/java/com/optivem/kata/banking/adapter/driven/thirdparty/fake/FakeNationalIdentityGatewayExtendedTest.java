package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeNationalIdentityGatewayExtendedTest {

    private FakeNationalIdentityGateway provider;

    @BeforeEach
    private void init() {
        this.provider = new FakeNationalIdentityGateway();
    }

    @Test
    void should_return_false_for_non_existent_national_identity_number() {
        var nationalIdentityNumber = "ABC";
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isFalse();
    }

    @Test
    void should_return_true_for_non_existent_national_identity_number() {
        var nationalIdentityNumber = "ABC";
        provider.setupExists(nationalIdentityNumber);
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isTrue();
    }
}
