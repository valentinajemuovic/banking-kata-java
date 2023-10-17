package com.optivem.kata.banking.adapter.driven.persistence.mongo;

import com.optivem.kata.banking.adapter.driven.persistence.mongo.internal.MongoTemplateCustomDataAccessor;
import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDefaults;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Objects;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class MongoBankAccountStorageTest {
    private BankAccountDto bankAccountDto;
    private final MongoTemplateCustomDataAccessor mongoTemplateCustomDataAccessor;
    private MongoBankAccountStorage mongoBankAccountStorage;

    public MongoBankAccountStorageTest() {
        this.mongoTemplateCustomDataAccessor = new MongoTemplateCustomDataAccessor();
    }

    @BeforeAll
    public void setUp() {
        bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountId(BankAccountDefaults.DEFAULT_ACCOUNT_ID);
        bankAccountDto.setAccountNumber(BankAccountDefaults.DEFAULT_ACCOUNT_NUMBER);
        bankAccountDto.setFirstName(BankAccountDefaults.DEFAULT_FIRST_NAME);
        bankAccountDto.setLastName(BankAccountDefaults.DEFAULT_LAST_NAME);
        bankAccountDto.setOpeningDate(BankAccountDefaults.DEFAULT_OPENING_DATE);
        bankAccountDto.setNationalIdentityNumber(BankAccountDefaults.DEFAULT_NATIONAL_IDENTITY_NUMBER);
        bankAccountDto.setBalance(BankAccountDefaults.DEFAULT_BALANCE);
        mongoBankAccountStorage = new MongoBankAccountStorage(this.mongoTemplateCustomDataAccessor);
    }

    @AfterEach
    public void clean_up() {
        var template = this.mongoTemplateCustomDataAccessor.getMongoTemplate();
        template.dropCollection(MongoBankAccountStorage.COLLECTION_NAME);
    }

    @AfterAll
    public void tearDown() {
        this.mongoTemplateCustomDataAccessor.close();
    }

    @Test
    void not_should_get_bank_account_if_not_found() {
        Optional<BankAccountDto> bankAccountDto = mongoBankAccountStorage.find("1");
        assertTrue(bankAccountDto.isEmpty());
    }

    @Test
    void should_create_new_bank_account() {
        mongoBankAccountStorage.add(bankAccountDto);
        Optional<BankAccountDto> output = mongoBankAccountStorage.find(bankAccountDto.getAccountNumber());
        assertEquals(Objects.requireNonNull(output.stream().findFirst().orElse(null)).getAccountNumber(), bankAccountDto.getAccountNumber());
    }

    @Test
    void should_update_bank_account() {
        mongoBankAccountStorage.add(bankAccountDto);
        assertEquals(BankAccountDefaults.DEFAULT_FIRST_NAME, bankAccountDto.getFirstName());
        bankAccountDto.setBalance(1000);
        mongoBankAccountStorage.update(bankAccountDto);
        Optional<BankAccountDto> output = mongoBankAccountStorage.find(bankAccountDto.getAccountNumber());
        assertEquals(1000, output.stream().findFirst().orElseThrow().getBalance());
    }
}
