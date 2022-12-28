package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.common.data.MethodSources.NON_POSITIVE_LONGS;

public class AccountIdTest {
    @ParameterizedTest
    @MethodSource(NON_POSITIVE_LONGS)
    void should_throw_exception_given_non_positive_value() {
        verifyThat(() -> AccountId.of(0))
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_ID_NON_POSITIVE);
    }
}
