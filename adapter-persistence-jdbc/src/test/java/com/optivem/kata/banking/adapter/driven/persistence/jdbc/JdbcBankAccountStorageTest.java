package com.optivem.kata.banking.adapter.driven.persistence.jdbc;

import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.adapter.driven.base.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BankingApplication.class)
@ActiveProfiles({ProfileNames.ADAPTER_PERSISTENCE_JDBC, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_MICROSERVICE_SIM, ProfileNames.ADAPTER_THIRDPARTY_SIM})
@Sql({"/bank_account_table.sql"})
public class JdbcBankAccountStorageTest extends BankAccountStorageTest {
    @Autowired
    public JdbcBankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        super(storage, accountIdGenerator, accountNumberGenerator);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcBankAccountStorage jdbcBankAccountStorage;

    @BeforeEach
    void init() {
        jdbcBankAccountStorage = new JdbcBankAccountStorage(jdbcTemplate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "67890"})
    void shouldAddAndFind(String accountNumber) {
        var bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNumber(accountNumber);

        jdbcBankAccountStorage.add(bankAccountDto);

        Optional<BankAccountDto> foundAccountDto = jdbcBankAccountStorage.find(accountNumber);
        assertTrue(foundAccountDto.isPresent());
        assertEquals(accountNumber, foundAccountDto.get().getAccountNumber());
    }
}
