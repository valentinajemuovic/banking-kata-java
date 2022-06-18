package com.optivem.kata.banking.web.controllers;

import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.web.controllers.base.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController extends BaseController {

    @PostMapping("/bank-accounts")
    public ResponseEntity<OpenAccountResponse> openAccount(@RequestBody OpenAccountRequest request) {
        var response = pipeline.send(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
