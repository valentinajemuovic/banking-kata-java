package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.aViewAccountRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class FacadeVerify {
    private final Facade facade;

    public FacadeVerify(Facade facade) {
        this.facade = facade;
    }

    public FacadeDepositFundsRequestVerify withRequest(DepositFundsRequest request) {
        return new FacadeDepositFundsRequestVerify(facade, request);
    }

    public void shouldContain(String accountNumber, int expectedFinalBalance) {
        var request = aViewAccountRequest()
                .accountNumber(accountNumber)
                .build();

        var response = facade.execute(request);

        assertThat(response).isNotNull();
    }
}
