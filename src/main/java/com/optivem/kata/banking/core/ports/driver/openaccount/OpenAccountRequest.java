package com.optivem.kata.banking.core.ports.driver.openaccount;

import an.awesome.pipelinr.Command;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OpenAccountRequest implements Command<OpenAccountResponse> {
    private String firstName;
    private String lastName;
    private int balance;
}
