package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.scoring.NameFactorCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDtoTestBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

public class NameFactorCalculatorTest {
    private NameFactorCalculator factorCalculator;

    @BeforeEach
    void init() {
        factorCalculator = new NameFactorCalculator();
    }

    private static Stream<Arguments> should_return_5_given_that_total_name_length_is_less_than_or_equal_to_5() {
        return Stream.of(Arguments.of("M", "S"),
                Arguments.of("Ma", "S"),
                Arguments.of("Mary", "S"),
                Arguments.of("Ja", "Smi"));
    }

    @ParameterizedTest
    @MethodSource
    void should_return_5_given_that_total_name_length_is_less_than_or_equal_to_5(String firstName, String lastName) {
        var bankAccount = bankAccount()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildEntity();

        var expectedResult = 3;

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> should_return_total_name_length_given_that_total_name_length_is_greater_than_5_or_less_than_equal_to_10() {
        return Stream.of(Arguments.of("Mary", "Sm", 6),
                Arguments.of("Mary", "Smith", 9),
                Arguments.of("Ja", "McDonald", 10));
    }

    @ParameterizedTest
    @MethodSource
    void should_return_total_name_length_given_that_total_name_length_is_greater_than_5_or_less_than_equal_to_10(String firstName, String lastName, int expectedResult) {
        var bankAccount = bankAccount()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildEntity();

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> should_return_total_name_length_times_3_given_that_total_name_length_is_greater_than_10() {
        return Stream.of(Arguments.of("Ja", "McDonalds", 33),
                Arguments.of("Joannes", "Smith", 36));
    }

    @ParameterizedTest
    @MethodSource
    void should_return_total_name_length_times_3_given_that_total_name_length_is_greater_than_10(String firstName, String lastName, int expectedResult) {
        var bankAccount = bankAccount()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildEntity();

        var result = factorCalculator.calculate(bankAccount);

        assertThat(result).isEqualTo(expectedResult);
    }
}
