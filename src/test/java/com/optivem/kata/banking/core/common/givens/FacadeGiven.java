package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.domain.accounts.BankAccountRepository;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.anOpenAccountRequest;

public class FacadeGiven {
    private final Facade facade;

    public FacadeGiven(Facade facade) {
        this.facade = facade;
    }

    public String alreadyHasBankAccount(int balance) {
        var openAccountRequest = anOpenAccountRequest()
                .balance(balance)
                .build();

        var openAccountResponse = facade.execute(openAccountRequest);
        return openAccountResponse.getAccountNumber();
    }
}
