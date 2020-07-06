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

import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.services.TransactionTypeService;

@RestController
@RequestMapping("/transaction-type")
public class TransactionTypeController {
    private TransactionTypeService service;
    
    public TransactionTypeController(TransactionTypeService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<TransactionType>> getAll() {
        List<TransactionType> transactionTypes = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(transactionTypes);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<TransactionType>> getById(@PathVariable("id") Long id) {
        Optional<TransactionType> transactionType = this.service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionType);
    }
    
    @PostMapping
    public ResponseEntity<TransactionType> create(@RequestBody TransactionType transactionType) {
    	this.service.create(transactionType);
        return ResponseEntity.status(HttpStatus.OK).body(transactionType);
    }
    
    @PutMapping
    public ResponseEntity<TransactionType> update(@RequestBody TransactionType transactionType) {
    	this.service.update(transactionType);
        return ResponseEntity.status(HttpStatus.OK).body(transactionType);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    	this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}