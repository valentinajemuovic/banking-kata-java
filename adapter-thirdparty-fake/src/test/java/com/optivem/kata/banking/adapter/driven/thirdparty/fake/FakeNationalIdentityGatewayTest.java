package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.NationalIdentityGatewayTest;
import org.junit.jupiter.api.BeforeEach;

public class FakeNationalIdentityGatewayTest extends NationalIdentityGatewayTest<FakeNationalIdentityGateway> {

    @BeforeEach
    void init() {
        provider = new FakeNationalIdentityGateway();
    }

    @Override
    protected void setupExistentUser(String nationalIdentityNumber) {
        provider.setupExistent(nationalIdentityNumber);
    }
}
