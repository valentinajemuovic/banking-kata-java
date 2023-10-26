package com.optivem.kata.banking.adapter.driven.persistence.jpa.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccountRecord {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "national_identity_number", nullable = false)
    private String nationalIdentityNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Column(name = "balance", nullable = false)
    private int balance;
}
