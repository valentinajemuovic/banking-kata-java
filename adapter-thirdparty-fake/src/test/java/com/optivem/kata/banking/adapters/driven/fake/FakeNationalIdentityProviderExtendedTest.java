package com.optivem.kata.banking.adapters.driven.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeNationalIdentityProviderExtendedTest {

    private FakeNationalIdentityProvider provider;

    @BeforeEach
    private void init() {
        this.provider = new FakeNationalIdentityProvider();
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
        provider.givenExists(nationalIdentityNumber);
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isTrue();
    }
}
