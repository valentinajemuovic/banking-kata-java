package com.optivem.kata.banking.core.ports.driver.accounts.viewaccount;

import an.awesome.pipelinr.Command;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewAccountRequest implements Command<ViewAccountResponse> {
    private String accountNumber;
}
