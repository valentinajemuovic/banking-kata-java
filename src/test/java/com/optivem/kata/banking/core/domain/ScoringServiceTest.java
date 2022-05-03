package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.FactorAggregator;
import com.optivem.kata.banking.core.domain.accounts.scoring.Score;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoringService;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoringServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ScoringServiceTest {

    private FactorAggregator factorAggregator;
    private ScoringService scoringService;

    @BeforeEach
    void init() {
        factorAggregator = mock(FactorAggregator.class);
        scoringService = new ScoringServiceImpl(factorAggregator);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7})
    void should_return_score_A_given_positive_odd_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.A;

        var score = scoringService.calculateScore(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 10})
    void should_return_score_B_given_positive_even_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.B;

        var score = scoringService.calculateScore(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -5, -10})
    void should_return_score_C_given_non_positive_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.C;

        var score = scoringService.calculateScore(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

}
