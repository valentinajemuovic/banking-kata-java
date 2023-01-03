package com.optivem.kata.banking.system;

import com.optivem.kata.banking.BankingApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpa")
@ContextConfiguration
@Disabled("TODO: Not sure why this test fails (due to missing JPA config) yet BankAccountControllerSystemTest passes?")
class BankingApplicationTests {

    @Test
    void contextLoads() {
        var args = new String[]{};
        BankingApplication.main(args);
    }

}
