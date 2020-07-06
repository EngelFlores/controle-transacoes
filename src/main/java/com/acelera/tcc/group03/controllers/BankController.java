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

import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.services.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
    private BankService service;

    public BankController(BankService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Bank>> getAll() {
        List<Bank> banks = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(banks);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Bank>> getBank(@PathVariable("id") Long id) {
        Optional<Bank> bank = this.service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bank);
    }
    
    @PostMapping
    public ResponseEntity<Bank> create(@RequestBody Bank bank) {
    	this.service.create(bank);
        return ResponseEntity.status(HttpStatus.OK).body(bank);
    }
    
    @PutMapping
    public ResponseEntity<Bank> update(@RequestBody Bank bank) {
    	this.service.update(bank);
        return ResponseEntity.status(HttpStatus.OK).body(bank);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}