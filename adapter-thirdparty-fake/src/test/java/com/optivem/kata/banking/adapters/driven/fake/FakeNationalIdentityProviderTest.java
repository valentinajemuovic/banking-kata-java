package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.adapters.driven.NationalIdentityProviderTest;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;

public class FakeNationalIdentityProviderTest extends NationalIdentityProviderTest {
    @Override
    protected NationalIdentityProvider create() {
        var provider = new FakeNationalIdentityProvider();
        provider.add("Me"); // TODO: VC: Simulator
        return provider;
    }
}
