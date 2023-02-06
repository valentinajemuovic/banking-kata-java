package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.NationalIdentityGatewayTest;
import org.junit.jupiter.api.BeforeEach;

public class FakeNationalIdentityGatewayTest extends NationalIdentityGatewayTest {

    @BeforeEach
    void init() {
        provider = new FakeNationalIdentityGateway();
    }

    @Override
    public void should_return_true_when_user_exists() {
        provider.exists(EXISTING_ID);
        super.should_return_true_when_user_exists();
    }
}
