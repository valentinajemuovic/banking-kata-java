package com.optivem.kata.banking.adapter.driver.restapi.spring.configurations;

import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    @Bean
    public ScoreCalculator scoreCalculator(DateTimeService dateTimeService) {
        return DefaultScoreCalculator.create(dateTimeService);
    }
}
