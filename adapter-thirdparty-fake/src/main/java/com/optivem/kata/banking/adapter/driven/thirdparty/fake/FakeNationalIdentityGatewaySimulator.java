package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.ADAPTER_THIRDPARTY_SIM)
public class FakeNationalIdentityGatewaySimulator implements NationalIdentityGateway {

    private final FakeNationalIdentityGateway provider;

    public FakeNationalIdentityGatewaySimulator() {
        this.provider = new FakeNationalIdentityGateway();
        provider.setupExists("SIM_1");
        provider.setupExists("SIM_2");
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        return provider.exists(nationalIdentityNumber);
    }
}
