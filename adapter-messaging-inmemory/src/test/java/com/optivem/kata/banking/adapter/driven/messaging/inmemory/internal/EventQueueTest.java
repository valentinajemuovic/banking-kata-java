package com.optivem.kata.banking.adapter.driven.messaging.inmemory.internal;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventQueueTest {
  @Test
  void shouldAddAndRetrieveEvents() {
    var queue = new EventQueue<String>();
    var event = "Test Event";

    queue.addEvent(event);
    assertEquals(event, queue.next());
  }

  @Test
  void shouldThrowNoSuchElementExceptionWhenQueueIsEmpty() {
    var queue = new EventQueue<String>();
    assertThrows(NoSuchElementException.class, queue::next);
  }
}
