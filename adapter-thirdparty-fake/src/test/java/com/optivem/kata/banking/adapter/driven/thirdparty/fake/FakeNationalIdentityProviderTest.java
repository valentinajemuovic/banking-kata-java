package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.NationalIdentityProviderTest;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.junit.jupiter.api.BeforeEach;

public class FakeNationalIdentityProviderTest extends NationalIdentityProviderTest {

    @BeforeEach
    void init() {
        provider = new FakeNationalIdentityProvider();
    }

    @Override
    public void should_return_true_when_user_exists() {
        provider.exists(EXISTING_ID);
        super.should_return_true_when_user_exists();
    }
}
