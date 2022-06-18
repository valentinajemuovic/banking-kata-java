package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.common.builders.entities.BankAccountTestBuilder;
import com.optivem.kata.banking.core.common.builders.entities.BankAccountDefaults;
import com.optivem.kata.banking.core.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.Balance;
import com.optivem.kata.banking.core.domain.accounts.BankAccountBuilder;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static com.optivem.kata.banking.core.domain.accounts.BankAccountBuilder.bankAccount;
import static org.assertj.core.api.Assertions.assertThat;

class BankAccountTest {

    @Test
    void should_construct_given_valid_data() {
        var bankAccount = getDefaultBuilder().build();
        assertThat(bankAccount).isNotNull();
    }

    @Test
    void should_throw_exception_given_null_account_number() {
        verifyThat(() -> getDefaultBuilder().withAccountNumber(null).build())
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    @Test
    void should_throw_exception_given_null_account_holder_name() {
        verifyThat(() -> getDefaultBuilder().withAccountHolderName(null).build())
                .shouldThrowValidationException(ValidationMessages.ACCOUNT_HOLDER_NAME_EMPTY);
    }

    @Test
    void should_throw_exception_given_null_opening_date() {
        verifyThat(() -> getDefaultBuilder().withOpeningDate(null).build())
                .shouldThrowValidationException(ValidationMessages.OPENING_DATE_EMPTY);
    }


    @Test
    void should_throw_exception_given_null_balance() {
        verifyThat(() -> getDefaultBuilder().withBalance(null).build())
                .shouldThrowValidationException(ValidationMessages.BALANCE_EMPTY);
    }

    private BankAccountBuilder getDefaultBuilder() {
        var accountNumber = AccountNumber.of(BankAccountDefaults.DEFAULT_ACCOUNT_NUMBER);
        var accountHolderName = AccountHolderName.of(BankAccountDefaults.DEFAULT_FIRST_NAME, BankAccountDefaults.DEFAULT_LAST_NAME);
        var openingDate = BankAccountDefaults.DEFAULT_OPENING_DATE;
        var balance = Balance.of(BankAccountDefaults.DEFAULT_BALANCE);

        return BankAccountBuilder.bankAccount().withAccountNumber(accountNumber)
                .withAccountHolderName(accountHolderName)
                .withOpeningDate(openingDate)
                .withBalance(balance);
    }
}
