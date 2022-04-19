package com.optivem.kata.banking.core.usecases.withdrawfunds;

import com.optivem.kata.banking.core.usecases.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class WithdrawFundsRequest implements Request<WithdrawFundsResponse> {
    private String accountNumber;
}
