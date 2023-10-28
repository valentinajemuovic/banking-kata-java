package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileNames.ADAPTER_MICROSERVICE_SIM)
public class FakeCustomerGatewaySimulator implements CustomerGateway {

    private final FakeCustomerGateway provider;

    @Autowired
    public FakeCustomerGatewaySimulator(FakeCustomerGateway provider) {
        this.provider = provider;
    }

    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        return provider.isBlacklisted(nationalIdentityNumber);
    }
}
