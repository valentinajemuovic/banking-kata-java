package com.optivem.kata.banking.core.usecases.viewaccount;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewAccountRequest {
    private String accountNumber;
}
