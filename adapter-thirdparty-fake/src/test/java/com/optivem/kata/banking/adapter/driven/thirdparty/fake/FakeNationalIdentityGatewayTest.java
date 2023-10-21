package com.optivem.kata.banking.adapter.driven.thirdparty.fake;

import com.optivem.kata.banking.adapter.driven.base.NationalIdentityGatewayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FakeNationalIdentityGatewayTest extends NationalIdentityGatewayTest<FakeNationalIdentityGateway> {

    @BeforeEach
    void init() {
        provider = new FakeNationalIdentityGateway();
    }

    @Override
    protected void setupExistentUser(String nationalIdentityNumber) {
        provider.setupExistent(nationalIdentityNumber);
    }

    @Test
    void shouldReturnTrueWithExistentUser() {
        var existentNationalIdentityNumber = "12345";
        setupExistentUser(existentNationalIdentityNumber);

        var exists = provider.exists(existentNationalIdentityNumber);
        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseWithNonExistentUser() {
        var nonExistentNationalIdentityNumber = "67890";
        var exists = provider.exists(nonExistentNationalIdentityNumber);
        assertFalse(exists);
    }
}
