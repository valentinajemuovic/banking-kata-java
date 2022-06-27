package com.optivem.kata.banking.core.ports.driver.viewaccount;

import com.optivem.kata.banking.core.cleanarch.domain.scoring.Score;
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
