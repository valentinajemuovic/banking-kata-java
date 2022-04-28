package com.optivem.kata.banking.core.usecases.viewaccount;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ViewAccountResponse {
    private String accountNumber;
    private String fullName;
    private int balance;
}
