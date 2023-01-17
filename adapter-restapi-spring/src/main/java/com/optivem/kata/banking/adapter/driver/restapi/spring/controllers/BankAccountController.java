package com.optivem.kata.banking.adapter.driver.restapi.spring.controllers;

import com.optivem.kata.banking.adapter.driver.restapi.spring.controllers.base.BaseController;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController extends BaseController {

    @PostMapping("/bank-accounts")
    public ResponseEntity<OpenAccountResponse> openAccount(@RequestBody OpenAccountRequest request) {
        var response = pipeline.send(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/bank-accounts/{accountNumber}")
    public ResponseEntity<ViewAccountResponse> viewAccount(@PathVariable String accountNumber) {
        var request = ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();

        try {
            var response = pipeline.send(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(ValidationException ex) {
            if(ex.getMessage().equals(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }
}
