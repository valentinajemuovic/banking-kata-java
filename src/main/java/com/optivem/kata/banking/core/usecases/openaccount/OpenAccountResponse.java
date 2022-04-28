package com.optivem.kata.banking.core.usecases.openaccount;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OpenAccountResponse {
    private String accountNumber;
}
