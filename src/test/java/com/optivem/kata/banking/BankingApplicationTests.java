package com.optivem.kata.banking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankingApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1 == 1).isTrue();
	}

}
