package com.optivem.kata.banking.adapters.time.system;

import com.optivem.kata.banking.adapters.driven.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
// @Profile(ProfileNames.AdapterTimeSystem) // TODO: VC: Not working for BankingProviderContractTest
@Primary
public class SysDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
