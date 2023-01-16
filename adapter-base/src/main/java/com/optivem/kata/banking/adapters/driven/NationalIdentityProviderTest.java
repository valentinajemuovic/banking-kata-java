package com.optivem.kata.banking.adapters.driven;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class NationalIdentityProviderTest {
    protected NationalIdentityProvider provider;

    @BeforeEach
    private void init() {
        this.provider = create();
    }

    protected abstract NationalIdentityProvider create();

    @Test
    void should_return_response() {
        var nationalIdentityNumber = "123";
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isNotNull();
    }

    // TODO: VC: Scenario of return success
    // TODO: VC: Scenario of return not exist
}
