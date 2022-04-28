package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountRequest;

public class ViewAccountRequestBuilder {

    private static final String ACCOUNT_NUMBER = "GB51BARC20031816295685";

    private String accountNumber;

    public ViewAccountRequestBuilder() {
        accountNumber(ACCOUNT_NUMBER);
    }

    public static ViewAccountRequestBuilder aViewAccountRequest() {
        return new ViewAccountRequestBuilder();
    }

    public ViewAccountRequest build() {
        var request = new ViewAccountRequest();
        request.setAccountNumber(accountNumber);
        return request;
    }

    public ViewAccountRequestBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
}
