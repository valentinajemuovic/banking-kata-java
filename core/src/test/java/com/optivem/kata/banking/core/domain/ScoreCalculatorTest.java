package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.FactorAggregator;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.Score;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.DefaultScoreCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ScoreCalculatorTest {

    private FactorAggregator factorAggregator;
    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void init() {
        factorAggregator = mock(FactorAggregator.class);
        scoreCalculator = new DefaultScoreCalculator(factorAggregator);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7})
    void should_return_score_A_given_positive_odd_aggregation(int aggregationResult) {
        assertScore(aggregationResult, Score.A);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 10})
    void should_return_score_B_given_positive_even_aggregation(int aggregationResult) {
        assertScore(aggregationResult, Score.B);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -5, -10})
    void should_return_score_C_given_non_positive_aggregation(int aggregationResult) {
        assertScore(aggregationResult, Score.C);
    }

    private void assertScore(int aggregationResult, Score expectedScore) {
        var bankAccount = bankAccount().buildEntity();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);

        var score = scoreCalculator.calculate(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

}
