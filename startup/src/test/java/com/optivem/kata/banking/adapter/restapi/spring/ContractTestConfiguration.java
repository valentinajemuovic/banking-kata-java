package com.optivem.kata.banking.adapter.restapi.spring;

import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("contract-test")
public class ContractTestConfiguration {

    @Bean
    // @Primary
    public DateTimeService helloDateTime() {
        return null;
    }

    @Bean
    public BankAccountStorage helloStorage() {return null;}

}
