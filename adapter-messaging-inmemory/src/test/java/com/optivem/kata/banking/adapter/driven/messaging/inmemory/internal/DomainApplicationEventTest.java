package com.optivem.kata.banking.adapter.driven.messaging.inmemory.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainApplicationEventTest {
  @Test
  void shouldStoreEventData() {
    var data = "Test Data";
    var event = new DomainApplicationEvent<>(data, data);
    assertEquals(data, event.getData());
  }
}
