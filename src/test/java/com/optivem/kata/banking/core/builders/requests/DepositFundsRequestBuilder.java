package com.optivem.kata.banking.core.builders.requests;

import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;

public class DepositFundsRequestBuilder {

    private static final String ACCOUNT_NUMBER = "GB51BARC20031816295685";
    private static final int AMOUNT = 600;

    private String accountNumber;
    private int amount;

    public static DepositFundsRequestBuilder aDepositFundsRequest() {
        return new DepositFundsRequestBuilder();
    }

    public DepositFundsRequestBuilder() {
        accountNumber(ACCOUNT_NUMBER);
        amount(AMOUNT);
    }

    public DepositFundsRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public DepositFundsRequestBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public DepositFundsRequest build() {
        var request = new DepositFundsRequest();
        request.setAccountNumber(accountNumber);
        request.setAmount(amount);
        return request;
    }
}
