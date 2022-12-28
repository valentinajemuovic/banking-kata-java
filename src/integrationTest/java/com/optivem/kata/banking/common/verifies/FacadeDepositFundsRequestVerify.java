package com.optivem.kata.banking.common.verifies;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.ports.driver.accounts.depositfunds.DepositFundsRequest;

import static com.optivem.kata.banking.common.Verifications.verifyThat;

public class FacadeDepositFundsRequestVerify {
    private final Facade facade;
    private final DepositFundsRequest request;

    public FacadeDepositFundsRequestVerify(Facade facade, DepositFundsRequest request) {
        this.facade = facade;
        this.request = request;
    }

    public void shouldExecuteSuccessfully() {
        facade.execute(request);
    }

    public void shouldThrowValidationException(String message) {
        verifyThat(() -> facade.execute(request)).shouldThrowValidationException(message);
    }
}
