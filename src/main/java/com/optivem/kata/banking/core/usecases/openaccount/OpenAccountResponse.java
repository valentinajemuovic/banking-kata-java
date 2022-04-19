package com.optivem.kata.banking.core.usecases.openaccount;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class OpenAccountResponse {
    private String accountNumber;
}
