package com.optivem.kata.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable String id) {
        return null;

        // var customer = customerService.getCustomer(id);
    }


    /*

    @GetMapping("/bank-accounts/{accountNumber}")
    public ResponseEntity<ViewAccountResponse> viewAccount(@PathVariable String accountNumber) {
        var request = ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();


     */
}
