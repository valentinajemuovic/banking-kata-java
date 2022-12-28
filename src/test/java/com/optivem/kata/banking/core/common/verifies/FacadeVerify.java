package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;

import static com.optivem.kata.banking.core.common.builders.requests.ViewAccountRequestBuilder.viewAccountRequest;
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
        var request = viewAccountRequest()
                .withAccountNumber(accountNumber)
                .build();

        var response = facade.execute(request);

        assertThat(response).isNotNull();
    }
}
