package com.optivem.kata.banking.adapter.driven.persistence.redis.mapper;

import com.optivem.kata.banking.adapter.driven.persistence.redis.internal.BankAccountModel;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountMapperTest {

    private static final long ACCOUNT_ID = 405109388022382592L;
    private static final String ACCOUNT_NUMBER = "01GQDNMHJ1W5CAM8V4V4MVNHW";
    private static final String NATIONAL_IDENTITY_NUMBER = "XYZ_2";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Bunyan";
    private static final LocalDate OPENING_DATE = LocalDate.of(2023, 1, 21);
    private static final String NEW_LASTNAME = "Wycliffe";
    private static final int DEFAULT_BALANCE = 500;
    private static final int NEW_BALANCE = 650;

    private BankAccountMapper bankAccountMapper;

    @BeforeEach
    void setUp() {
        bankAccountMapper = new BankAccountMapperImpl();
    }

    @Test
    void should_map_to_dto() {
        var expectedDto = buildDto();
        var model = buildModel();

        var actualDto = bankAccountMapper.toDto(model);

        assertThat(actualDto).usingRecursiveComparison().isEqualTo(expectedDto);
    }

    @Test
    void should_map_to_model() {
        var expectedDto = buildDto();
        var expectedModel = buildModel();

        var actualModel = bankAccountMapper.toModel(expectedDto);

        assertThat(actualModel).usingRecursiveComparison().isEqualTo(expectedModel);
    }

    @Test
    void should_map_only_filled_atributtes() {
        var baseModel = buildModel();
        var dtoWithAtibutteToUpdate = BankAccountDto.builder()
                .lastName(NEW_LASTNAME)
                .balance(NEW_BALANCE)
                .build();

        var updatedModel = bankAccountMapper.toModel(dtoWithAtibutteToUpdate, baseModel);

        assertThat(updatedModel.getLastName()).isEqualTo(NEW_LASTNAME);
        assertThat(updatedModel.getBalance()).isEqualTo(NEW_BALANCE);
        assertThat(updatedModel.getAccountId()).isEqualTo(baseModel.getAccountId());
        assertThat(updatedModel.getAccountNumber()).isEqualTo(baseModel.getAccountNumber());
        assertThat(updatedModel.getNationalIdentityNumber()).isEqualTo(baseModel.getNationalIdentityNumber());
        assertThat(updatedModel.getFirstName()).isEqualTo(baseModel.getFirstName());
        assertThat(updatedModel.getOpeningDate()).isEqualTo(baseModel.getOpeningDate());
    }

    private static BankAccountDto buildDto() {
        return BankAccountDto.builder()
                .accountId(ACCOUNT_ID)
                .accountNumber(ACCOUNT_NUMBER)
                .nationalIdentityNumber(NATIONAL_IDENTITY_NUMBER)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .openingDate(OPENING_DATE)
                .balance(DEFAULT_BALANCE)
                .build();
    }

    private static BankAccountModel buildModel() {
        return BankAccountModel.builder()
                .accountId(ACCOUNT_ID)
                .accountNumber(ACCOUNT_NUMBER)
                .nationalIdentityNumber(NATIONAL_IDENTITY_NUMBER)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .openingDate(OPENING_DATE)
                .balance(DEFAULT_BALANCE)
                .build();
    }
}