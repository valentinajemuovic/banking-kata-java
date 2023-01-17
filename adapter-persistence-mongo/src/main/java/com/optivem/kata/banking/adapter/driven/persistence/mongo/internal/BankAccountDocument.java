package com.optivem.kata.banking.adapter.driven.persistence.mongo.internal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "bank_account")
public class BankAccountDocument {

    @Id
    @Field(name = "id")
    private long id;

    @Field(name = "account_number")
    private String accountNumber;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Field(name = "opening_date")
    private LocalDate openingDate;

    @Field(name = "balance")
    private String balance;

}
