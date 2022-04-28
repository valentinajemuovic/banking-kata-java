package com.optivem.kata.banking.core.usecases.viewaccount;

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
}
