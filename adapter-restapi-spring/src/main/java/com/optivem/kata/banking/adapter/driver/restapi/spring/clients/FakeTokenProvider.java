package com.optivem.kata.banking.adapter.driver.restapi.spring.clients;

public class FakeTokenProvider implements TokenProvider {
    @Override
    public String getToken() {
        return "Bearer SomeTokenABC";
    }
}
