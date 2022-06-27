package com.optivem.kata.banking.core.internal.cleanarch.domain.scoring;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;

public class NameFactorCalculator implements FactorCalculator {
    @Override
    public int calculate(BankAccount bankAccount) {
        var firstName = bankAccount.getAccountHolderName().firstName();
        var lastName = bankAccount.getAccountHolderName().lastName();

        var length = firstName.getLength() + lastName.getLength();

        if (length <= 5) {
            return 3;
        }

        if (length > 10) {
            return length * 3;
        }

        return length;
    }
}
