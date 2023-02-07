package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.adapter.driven.base.CustomerGatewayTest;
import org.junit.jupiter.api.BeforeEach;

public class FakeCustomerGatewayTest extends CustomerGatewayTest<FakeCustomerGateway> {

    @BeforeEach
    void init() {
        provider = new FakeCustomerGateway();
    }

    @Override
    protected void setupUserBlacklisted(String nationalIdentityNumber) {
        provider.setupBlacklisted(nationalIdentityNumber);
    }

    @Override
    protected void setupUserWhitelisted(String nationalIdentityNumber) {
        provider.setupWhitelisted(nationalIdentityNumber);
    }
}
