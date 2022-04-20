package com.optivem.kata.banking.core.builders.requests;

import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

public class DepositFundsRequestBuilder {

    private static final String ACCOUNT_NUMBER = "GB51BARC20031816295685";

    private String accountNumber;

    public static DepositFundsRequestBuilder aDepositFundsRequest() {
        return new DepositFundsRequestBuilder();
    }

    public DepositFundsRequestBuilder() {
        accountNumber(ACCOUNT_NUMBER);
    }

    public DepositFundsRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public DepositFundsRequest build() {
        var request = new DepositFundsRequest();
        request.setAccountNumber(accountNumber);
        return request;
    }
}
