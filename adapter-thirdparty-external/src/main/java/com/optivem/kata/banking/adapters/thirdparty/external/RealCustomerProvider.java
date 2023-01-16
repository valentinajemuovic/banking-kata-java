package com.optivem.kata.banking.adapters.thirdparty.external;

import com.optivem.kata.banking.core.ports.driven.CustomerProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RealCustomerProvider implements CustomerProvider {
    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        return false;
    }
}
