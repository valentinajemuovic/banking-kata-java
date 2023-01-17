package com.optivem.kata.banking.adapter.time.system;

import com.optivem.kata.banking.adapter.driven.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile(ProfileNames.AdapterTimeSystem)
public class SysDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
