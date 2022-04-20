package com.optivem.kata.banking.core.builders.requests;

import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;

public class OpenAccountRequestBuilder {

    private static final String FIRST_NAME = "Mary";
    private static final String LAST_NAME = "Jackson";
    private static final int INITIAL_BALANCE = 200;

    private String firstName;
    private String lastName;
    private int balance;

    public static OpenAccountRequestBuilder anOpenAccountRequest() {
        return new OpenAccountRequestBuilder();
    }

    public OpenAccountRequestBuilder() {
        firstName(FIRST_NAME);
        lastName(LAST_NAME);
        initialBalance(INITIAL_BALANCE);
    }

    public OpenAccountRequestBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public OpenAccountRequestBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public OpenAccountRequestBuilder initialBalance(int initialBalance) {
        this.balance = initialBalance;
        return this;
    }

    public OpenAccountRequest build() {
        var request = new OpenAccountRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setInitialBalance(balance);
        return request;
    }
}
