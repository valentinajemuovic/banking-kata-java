package com.optivem.kata.banking.core.usecases.viewaccount;

import com.optivem.kata.banking.core.domain.accounts.scoring.Score;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewAccountResponse {
    private String accountNumber;
    private String fullName;
    private int balance;
    private Score score;
}
