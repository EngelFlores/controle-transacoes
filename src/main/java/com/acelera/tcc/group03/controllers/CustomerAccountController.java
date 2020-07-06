package com.acelera.tcc.group03.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.services.CustomerAccountService;

@RestController
@RequestMapping("/customer-account")
public class CustomerAccountController {
    private CustomerAccountService service;
    
    public CustomerAccountController(CustomerAccountService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerAccount>> getAll() {
        List<CustomerAccount> customerAccounts = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(customerAccounts);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<CustomerAccount>> getCustomerAccount(@PathVariable("id") Long id) {
        Optional<CustomerAccount> customerAccount = this.service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
    }
    
    @PostMapping
    public ResponseEntity<CustomerAccount> create(@RequestBody CustomerAccount customerAccount) {
        this.service.create(customerAccount);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
    }
    
    @PutMapping
    public ResponseEntity<CustomerAccount> update(@RequestBody CustomerAccount customerAccount) {
        this.service.update(customerAccount);
        return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCostumerAccount(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}