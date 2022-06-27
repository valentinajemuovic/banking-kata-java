package com.optivem.kata.banking.web.configurations;

import com.optivem.kata.banking.core.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.domain.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.ports.driven.DateTimeServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Autowired
    private DateTimeServicePort dateTimeService;

    @Bean
    public ScoreCalculator scoreCalculator() {
        return DefaultScoreCalculator.create(dateTimeService);
    }
}
