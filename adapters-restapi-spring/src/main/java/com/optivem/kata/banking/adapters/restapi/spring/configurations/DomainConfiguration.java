package com.optivem.kata.banking.adapters.restapi.spring.configurations;

import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Autowired
    private DateTimeService dateTimeService;

    @Bean
    public ScoreCalculator scoreCalculator() {
        return DefaultScoreCalculator.create(dateTimeService);
    }
}
