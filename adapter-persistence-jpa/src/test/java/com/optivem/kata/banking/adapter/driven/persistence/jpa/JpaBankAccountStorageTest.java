package com.optivem.kata.banking.adapter.driven.persistence.jpa;

import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.adapter.driven.base.BankAccountStorageTest;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.BankAccountRecord;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.JpaBankAccountDataAccessor;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.mapper.BankAccountJpaMapper;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BankingApplication.class)
@ActiveProfiles({ ProfileNames.ADAPTER_PERSISTENCE_JPA, ProfileNames.ADAPTER_PERSISTENCE_REDIS, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_MICROSERVICE_SIM, ProfileNames.ADAPTER_THIRDPARTY_SIM})
@ContextConfiguration
public class JpaBankAccountStorageTest extends BankAccountStorageTest {
    @Autowired
    public JpaBankAccountStorageTest(BankAccountStorage storage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        super(storage, accountIdGenerator, accountNumberGenerator);
    }

    @InjectMocks
    private JpaBankAccountStorage jpaBankAccountStorage;

    @Mock
    private JpaBankAccountDataAccessor dataAccessor;

    @Mock
    private BankAccountJpaMapper bankAccountJpaMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFind() {
        var record = new BankAccountRecord();
        when(dataAccessor.findByAccountNumber(anyString())).thenReturn(Optional.of(record));

        var dto = new BankAccountDto();
        when(jpaBankAccountStorage.toDto(record)).thenReturn(dto);

        Optional<BankAccountDto> result = jpaBankAccountStorage.find("12345");

        assertEquals(Optional.of(dto), result);
    }

    @Test
    void shouldAdd() {
        var dto = new BankAccountDto();
        var record = new BankAccountRecord();
        when(jpaBankAccountStorage.create(dto)).thenReturn(record);

        jpaBankAccountStorage.add(dto);

        verify(dataAccessor, times(1)).save(record);
    }

    @Test
    void shouldUpdate() {
        var dto = new BankAccountDto();
        dto.setAccountNumber("12345");
        var record = new BankAccountRecord();
        when(dataAccessor.findByAccountNumber("12345")).thenReturn(Optional.of(record));

        jpaBankAccountStorage.update(dto);

        verify(bankAccountJpaMapper, times(1)).toRecord(dto, record);
        verify(dataAccessor, times(1)).save(any(BankAccountRecord.class));
    }
}
