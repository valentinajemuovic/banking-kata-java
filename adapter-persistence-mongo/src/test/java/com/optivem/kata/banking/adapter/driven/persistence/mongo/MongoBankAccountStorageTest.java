package com.optivem.kata.banking.adapter.driven.persistence.mongo;

import com.mongodb.client.MongoCollection;
import com.optivem.kata.banking.adapter.driven.persistence.mongo.internal.MongoTemplateCustomDataAccessor;
import com.optivem.kata.banking.core.common.builders.ports.driven.BankAccountDefaults;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class MongoBankAccountStorageTest {
    private BankAccountDto bankAccountDto;
    private final MongoTemplateCustomDataAccessor mongoTemplateCustomDataAccessor;
    private MongoBankAccountStorage mongoBankAccountStorage;

    public MongoBankAccountStorageTest() {
        this.mongoTemplateCustomDataAccessor = new MongoTemplateCustomDataAccessor();
    }

    @BeforeAll
    public void setUp() {
        this.bankAccountDto = new BankAccountDto();
        this.bankAccountDto.setAccountId(BankAccountDefaults.DEFAULT_ACCOUNT_ID);
        this.bankAccountDto.setAccountNumber(BankAccountDefaults.DEFAULT_ACCOUNT_NUMBER);
        this.bankAccountDto.setFirstName(BankAccountDefaults.DEFAULT_FIRST_NAME);
        this.bankAccountDto.setLastName(BankAccountDefaults.DEFAULT_LAST_NAME);
        this.bankAccountDto.setOpeningDate(BankAccountDefaults.DEFAULT_OPENING_DATE);
        this.bankAccountDto.setNationalIdentityNumber(BankAccountDefaults.DEFAULT_NATIONAL_IDENTITY_NUMBER);
        this.bankAccountDto.setBalance(BankAccountDefaults.DEFAULT_BALANCE);
        this.mongoBankAccountStorage = new MongoBankAccountStorage(this.mongoTemplateCustomDataAccessor);
    }

    @AfterEach
    public void clean_up()
    {
        MongoTemplate template = this.mongoTemplateCustomDataAccessor.getMongoTemplate();
        template.dropCollection(this.mongoBankAccountStorage.collectionName);
    }

    @AfterAll
    public void down() {
        this.mongoTemplateCustomDataAccessor.close();
    }

    @Test
    public void not_should_get_bank_account_if_not_found() {
        Optional<BankAccountDto> bankAccountDto = this.mongoBankAccountStorage.find("1");
        assertTrue(bankAccountDto.isEmpty());
    }

    @Test
    public void should_create_new_bank_account() {
        this.mongoBankAccountStorage.add(this.bankAccountDto);
        Optional<BankAccountDto> output = this.mongoBankAccountStorage.find(bankAccountDto.getAccountNumber());
        assertEquals(output.get().getAccountNumber(), bankAccountDto.getAccountNumber());
    }

    @Test
    public void should_update_bank_account() {
        this.mongoBankAccountStorage.add(this.bankAccountDto);
        assertEquals(BankAccountDefaults.DEFAULT_FIRST_NAME, this.bankAccountDto.getFirstName());
        this.bankAccountDto.setBalance(1000);
        this.mongoBankAccountStorage.update(this.bankAccountDto);
        Optional<BankAccountDto> output = this.mongoBankAccountStorage.find(bankAccountDto.getAccountNumber());
        assertEquals(1000, output.get().getBalance());
    }
}
