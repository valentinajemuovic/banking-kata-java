package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.CustomerGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.ADAPTER_MICROSERVICE_SIM)
public class FakeCustomerGatewaySimulator implements CustomerGateway {

    private final FakeCustomerGateway provider;

    public FakeCustomerGatewaySimulator() {
        this.provider = new FakeCustomerGateway();
    }

    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        return provider.isBlacklisted(nationalIdentityNumber);
    }
}
