package com.optivem.kata.banking.adapter.driven.microservice.real;

import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private boolean blacklisted;
}
