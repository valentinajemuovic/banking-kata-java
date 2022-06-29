package com.optivem.kata.banking.core.ports.driver.accounts.openaccount;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OpenAccountResponse {
    private String accountNumber;
}
