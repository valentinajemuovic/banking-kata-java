package com.optivem.kata.banking.core.facade.common;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder;
import com.optivem.kata.banking.core.ports.driven.*;

import java.time.LocalDateTime;

public class TestFacade {

    private final Facade facade;

    public TestFacade(Facade facade) {
        this.facade = facade;
    }

    public String alreadyHasBankAccount(int balance) {
        var openAccountRequest = OpenAccountRequestBuilder.openAccountRequest()
                .withBalance(balance)
                .build();

        var openAccountResponse = facade.execute(openAccountRequest);
        return openAccountResponse.getAccountNumber();
    }

    // TODO: VC: This is temporary, re-design to remove this
    public Facade getFacade() {
        return facade;
    }
}
