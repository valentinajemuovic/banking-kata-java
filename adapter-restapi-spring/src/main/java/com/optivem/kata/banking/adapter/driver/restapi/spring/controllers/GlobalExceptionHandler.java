package com.optivem.kata.banking.adapter.driver.restapi.spring.controllers;

import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handleValidationException(ValidationException ex) {
    HttpStatus status = getHttpStatusForValidationMessage(ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), status);
  }

  private HttpStatus getHttpStatusForValidationMessage(String message) {
    if (ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST.equals(message)) {
      return HttpStatus.NOT_FOUND;
    }
    return HttpStatus.BAD_REQUEST;
  }
}
