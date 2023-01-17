package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.AdapterThirdpartySim)
public class FakeNationalIdentityProviderSimulator implements NationalIdentityProvider {

    private final FakeNationalIdentityProvider provider;

    public FakeNationalIdentityProviderSimulator() {
        this.provider = new FakeNationalIdentityProvider();
        provider.setupExists("SIM_1");
        provider.setupExists("SIM_2");
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return provider.exists(nationalIdentityNumber);
    }
}
