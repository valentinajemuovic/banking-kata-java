package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.usecases.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class OpenAccountRequest implements Request<OpenAccountResponse> {
    private String firstName;
    private String lastName;
    private int initialBalance;
}
