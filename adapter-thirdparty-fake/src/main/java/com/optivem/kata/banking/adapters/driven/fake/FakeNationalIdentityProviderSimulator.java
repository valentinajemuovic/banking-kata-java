package com.optivem.kata.banking.adapters.driven.fake;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeNationalIdentityProviderSimulator implements NationalIdentityProvider {

    private final FakeNationalIdentityProvider provider;

    public FakeNationalIdentityProviderSimulator() {
        this.provider = new FakeNationalIdentityProvider();
        provider.add("SIM_1");
        provider.add("SIM_2");
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return provider.exists(nationalIdentityNumber);
    }
}
