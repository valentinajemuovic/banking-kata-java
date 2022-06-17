package com.optivem.kata.banking.core.common.givens;

import com.optivem.kata.banking.core.Facade;

import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.openAccountRequest;

public class FacadeGiven {
    private final Facade facade;

    public FacadeGiven(Facade facade) {
        this.facade = facade;
    }

    public String alreadyHasBankAccount(int balance) {
        var openAccountRequest = openAccountRequest()
                .withBalance(balance)
                .build();

        var openAccountResponse = facade.execute(openAccountRequest);
        return openAccountResponse.getAccountNumber();
    }
}
