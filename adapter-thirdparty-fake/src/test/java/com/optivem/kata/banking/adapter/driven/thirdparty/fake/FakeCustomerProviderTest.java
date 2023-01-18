package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.CustomerProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeCustomerProviderTest extends CustomerProviderTest<FakeCustomerProvider> {

    @BeforeEach
    void init() {
        provider = new FakeCustomerProvider();
    }

    @Override
    public void should_return_true_when_user_is_blacklisted() {
        provider.setupBlacklisted(BLACKLISTED_ID);
        super.should_return_true_when_user_is_blacklisted();
    }
}
