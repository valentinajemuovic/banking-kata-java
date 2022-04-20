package com.optivem.kata.banking.core.builders.requests;

import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

public class DepositFundsRequestBuilder {

    public static DepositFundsRequestBuilder aDepositFundsRequest() {
        return new DepositFundsRequestBuilder();
    }

    public DepositFundsRequestBuilder() {
    }

    public DepositFundsRequest build() {
        return new DepositFundsRequest();
    }
}
