package com.optivem.kata.banking.adapters.persistence.jpa.internal;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccountRecord {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Column(name = "balance", nullable = false)
    private int balance;
}
