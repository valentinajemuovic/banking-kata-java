package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.CustomerProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.ADAPTER_THIRDPARTY_SIM)
public class FakeCustomerProviderSimulator implements CustomerProvider {

    private final FakeCustomerProvider provider;

    public FakeCustomerProviderSimulator() {
        this.provider = new FakeCustomerProvider();
    }

    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        return provider.isBlacklisted(nationalIdentityNumber);
    }
}
