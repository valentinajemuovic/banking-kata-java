package com.optivem.kata.banking.adapter.driven.fake;

import com.optivem.kata.banking.adapter.driven.NationalIdentityProviderTest;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;

public class FakeNationalIdentityProviderTest extends NationalIdentityProviderTest {
    @Override
    protected NationalIdentityProvider create() {
        var provider = new FakeNationalIdentityProvider();
        provider.givenExists("Me"); // TODO: VC: Simulator
        return provider;
    }
}
