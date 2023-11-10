package com.optivem.kata.banking.adapter.driver.fake.auth.fake;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeAuthenticationProviderTest {
  private final  FakeAuthenticationProvider fakeAuthenticationProvider = new FakeAuthenticationProvider();

  @Test
  void shouldAuthenticateWithValidCredentials() {
    var token = new UsernamePasswordAuthenticationToken("user", "password");
    var result = fakeAuthenticationProvider.authenticate(token);
    assertNotNull(result);
    assertEquals("user", result.getName());
    assertTrue(result.getAuthorities().isEmpty());
  }

  @Test
  void shouldThrowExceptionWithNullCredentials() {
    assertThrows(IllegalArgumentException.class, () -> fakeAuthenticationProvider.authenticate(null));
  }

  @Test
  void shouldReturnAuthenticationWithEmptyAuthorities() {
    var token = new UsernamePasswordAuthenticationToken("user", "password");
    var result = fakeAuthenticationProvider.authenticate(token);
    assertTrue(result.getAuthorities().isEmpty());
  }

  @Test
  void shouldSupportUsernamePasswordAuthenticationToken() {
    assertTrue(fakeAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
  }
}
