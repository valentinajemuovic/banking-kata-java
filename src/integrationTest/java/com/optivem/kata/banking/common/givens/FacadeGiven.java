package com.optivem.kata.banking.common.givens;

import com.optivem.kata.banking.common.builders.requests.OpenAccountRequestBuilder;
import com.optivem.kata.banking.core.Facade;

public class FacadeGiven {
    private final Facade facade;

    public FacadeGiven(Facade facade) {
        this.facade = facade;
    }

    public String alreadyHasBankAccount(int balance) {
        var openAccountRequest = OpenAccountRequestBuilder.openAccountRequest()
                .withBalance(balance)
                .build();

        var openAccountResponse = facade.execute(openAccountRequest);
        return openAccountResponse.getAccountNumber();
    }
}
