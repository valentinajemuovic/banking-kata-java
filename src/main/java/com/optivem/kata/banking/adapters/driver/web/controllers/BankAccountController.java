package com.optivem.kata.banking.adapters.driver.web.controllers;

import com.optivem.kata.banking.adapters.driver.web.controllers.base.BaseController;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
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

        var response = pipeline.send(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
