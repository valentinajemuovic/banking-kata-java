package com.optivem.kata.banking.core.ports.driven.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundsDepositedDto implements EventDto {
    private LocalDateTime timestamp;
    private String accountNumber;
    private int amount;
}
