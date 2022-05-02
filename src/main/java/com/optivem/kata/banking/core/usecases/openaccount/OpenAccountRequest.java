package com.optivem.kata.banking.core.usecases.openaccount;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OpenAccountRequest {
    private String firstName;
    private String lastName;
    private int balance;
}
