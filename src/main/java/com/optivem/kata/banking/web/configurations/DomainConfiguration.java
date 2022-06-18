package com.optivem.kata.banking.web.configurations;

import com.optivem.kata.banking.core.domain.accounts.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.domain.time.DateTimeService;
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
