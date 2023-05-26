package com.optivem.kata.banking.adapter.driven.persistence.jdbc;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile(ProfileNames.ADAPTER_PERSISTENCE_JDBC)
public class JdbcBankAccountStorage implements BankAccountStorage {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBankAccountStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        return jdbcTemplate
                .queryForStream("SELECT * FROM bank_account WHERE account_number=?", toDtoMapper(), accountNumber)
                .findFirst();
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        jdbcTemplate.update(
                "INSERT INTO bank_account (id, account_number, national_identity_number, first_name, last_name, opening_date, balance) VALUES(?,?,?,?,?,?,?)",
                bankAccount.getAccountId(),
                bankAccount.getAccountNumber(),
                bankAccount.getNationalIdentityNumber(),
                bankAccount.getFirstName(),
                bankAccount.getLastName(),
                bankAccount.getOpeningDate(),
                bankAccount.getBalance());
    }

    @Override
    public void update(BankAccountDto bankAccountDto) {
        jdbcTemplate.update(
                "UPDATE bank_account SET account_number=?, national_identity_number=?, first_name=?, last_name=?, opening_date=?, balance=? WHERE id=?",
                bankAccountDto.getAccountNumber(),
                bankAccountDto.getNationalIdentityNumber(),
                bankAccountDto.getFirstName(),
                bankAccountDto.getLastName(),
                bankAccountDto.getOpeningDate(),
                bankAccountDto.getBalance(),
                bankAccountDto.getAccountId()
        );
    }

    private RowMapper<BankAccountDto> toDtoMapper() {
        return (rs, rowNum) -> BankAccountDto.builder()
                .accountId(rs.getLong("id"))
                .accountNumber(rs.getString("account_number"))
                .nationalIdentityNumber(rs.getString("national_identity_number"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .openingDate(rs.getDate("opening_date").toLocalDate())
                .balance(rs.getInt("balance"))
                .build();
    }

}
