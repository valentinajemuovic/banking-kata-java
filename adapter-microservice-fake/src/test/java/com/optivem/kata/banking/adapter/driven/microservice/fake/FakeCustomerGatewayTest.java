package com.optivem.kata.banking.adapter.driven.microservice.fake;

import com.optivem.kata.banking.adapter.driven.base.CustomerGatewayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @ParameterizedTest
    @CsvSource({
            "12345, BLACKLISTED, true",
            "12345, WHITELISTED, false",
            "67890, BLACKLISTED, true",
            "67890, WHITELISTED, false"
    })
    void shouldAssertBlacklisted(String nationalIdentityNumber, String setupMethod, boolean expected) {
        if (setupMethod.equals("BLACKLISTED")) {
            provider.setupBlacklisted(nationalIdentityNumber);
        } else {
            provider.setupWhitelisted(nationalIdentityNumber);
        }
        assertEquals(expected, provider.isBlacklisted(nationalIdentityNumber));
    }
}
