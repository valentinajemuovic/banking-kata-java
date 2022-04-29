package com.optivem.kata.banking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingApplicationTests {

    @Test
    void contextLoads() {
        var args = new String[]{};
        BankingApplication.main(args);
    }

}
