package com.optivem.kata.banking.core.usecases.depositfunds;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.optivem.kata.banking.core.usecases.VoidResponse;
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
