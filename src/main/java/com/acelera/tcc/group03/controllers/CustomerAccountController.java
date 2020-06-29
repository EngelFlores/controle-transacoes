package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.services.CustomerAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer-account")
public class CustomerAccountController {
    private CustomerAccountService customerAccountService;

    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @PostMapping
    public ResponseEntity<CustomerAccount> create(@RequestBody CustomerAccount customerAccount){
        customerAccountService.create(customerAccount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<CustomerAccount> update(@RequestBody CustomerAccount customerAccount){
        customerAccountService.update(customerAccount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<CustomerAccount>> getCustomerAccount(@PathVariable("id") Long id) {
        Optional<CustomerAccount> customerAccount = customerAccountService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCostumerAccount(@PathVariable("id") Long id){
        customerAccountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
