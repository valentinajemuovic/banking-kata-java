package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.FactorAggregator;
import com.optivem.kata.banking.core.domain.accounts.scoring.Score;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoreCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.ScoreCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ScoreCalculatorTest {

    private FactorAggregator factorAggregator;
    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void init() {
        factorAggregator = mock(FactorAggregator.class);
        scoreCalculator = new ScoreCalculatorImpl(factorAggregator);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7})
    void should_return_score_A_given_positive_odd_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.A;

        var score = scoreCalculator.calculate(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 10})
    void should_return_score_B_given_positive_even_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.B;

        var score = scoreCalculator.calculate(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -5, -10})
    void should_return_score_C_given_non_positive_aggregation(int aggregationResult) {
        var bankAccount = aBankAccount().build();
        given(factorAggregator.aggregate(bankAccount)).willReturn(aggregationResult);
        var expectedScore = Score.C;

        var score = scoreCalculator.calculate(bankAccount);

        assertThat(score).isEqualTo(expectedScore);
    }

}
