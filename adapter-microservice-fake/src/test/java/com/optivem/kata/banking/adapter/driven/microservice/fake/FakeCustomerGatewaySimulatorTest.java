package com.optivem.kata.banking.adapter.driven.microservice.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FakeCustomerGatewaySimulatorTest {
  @Mock
  private FakeCustomerGateway fakeCustomerGateway;

  private FakeCustomerGatewaySimulator fakeCustomerGatewaySimulator;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    fakeCustomerGatewaySimulator = new FakeCustomerGatewaySimulator(fakeCustomerGateway);
  }

  @Test
  void shouldVerifyBlacklisted() {
    String nationalIdentityNumber = "12345";
    when(fakeCustomerGateway.isBlacklisted(nationalIdentityNumber)).thenReturn(true);

    assertTrue(fakeCustomerGatewaySimulator.isBlacklisted(nationalIdentityNumber));

    verify(fakeCustomerGateway, times(1)).isBlacklisted(nationalIdentityNumber);
  }
}
