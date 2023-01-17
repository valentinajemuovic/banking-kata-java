package com.optivem.kata.banking.adapters.thirdparty.external;

import com.optivem.kata.banking.adapter.driven.NationalIdentityProviderTest;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;

public class RealNationalIdentityProviderTest extends NationalIdentityProviderTest {
    @Override
    protected NationalIdentityProvider create() {
        return new RealNationalIdentityProvider();
    }
}
