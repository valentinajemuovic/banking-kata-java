package com.optivem.kata.banking.core.common.builders.requests;

import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountRequest;

public class OpenAccountRequestBuilder {

    private static final String DEFAULT_FIRST_NAME = "Mary";
    private static final String DEFAULT_LAST_NAME = "Jackson";
    private static final int DEFAULT_INITIAL_BALANCE = 200;

    private String firstName;
    private String lastName;
    private int balance;

    public OpenAccountRequestBuilder() {
        this.firstName = DEFAULT_FIRST_NAME;
        this.lastName = DEFAULT_LAST_NAME;
        this.balance = DEFAULT_INITIAL_BALANCE;
    }

    public static OpenAccountRequestBuilder openAccountRequest() {
        return new OpenAccountRequestBuilder();
    }

    public OpenAccountRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public OpenAccountRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public OpenAccountRequestBuilder withBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public OpenAccountRequest build() {
        return OpenAccountRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .balance(balance)
                .build();
    }
}
