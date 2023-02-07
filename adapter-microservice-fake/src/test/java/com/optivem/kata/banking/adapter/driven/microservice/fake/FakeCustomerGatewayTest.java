package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.adapter.driven.base.CustomerGatewayTest;
import org.junit.jupiter.api.BeforeEach;

public class FakeCustomerGatewayTest extends CustomerGatewayTest<FakeCustomerGateway> {

    @BeforeEach
    void init() {
        provider = new FakeCustomerGateway();
    }

    @Override
    public void should_return_true_when_user_is_blacklisted() {
        provider.setupBlacklisted(BLACKLISTED_ID);
        super.should_return_true_when_user_is_blacklisted();
    }
}
