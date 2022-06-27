package com.optivem.kata.banking.core.ports.driver.depositfunds;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.ports.driver.VoidResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DepositFundsRequest implements Command<VoidResponse> {
    private String accountNumber;
    private int amount;
}
