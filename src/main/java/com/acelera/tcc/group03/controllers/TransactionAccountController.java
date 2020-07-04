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

import com.acelera.tcc.group03.domains.TransactionAccount;
import com.acelera.tcc.group03.services.TransactionAccountService;

@RestController
@RequestMapping("/transaction-account")
public class TransactionAccountController {
	private TransactionAccountService service;
	
	public TransactionAccountController(TransactionAccountService service) {
		this.service = service;
	}
	
    @GetMapping
    public ResponseEntity<List<TransactionAccount>> getAll() {
        List<TransactionAccount> transactionAccounts = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(transactionAccounts);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<TransactionAccount>> getById(@PathVariable("id") Long id) {
        Optional<TransactionAccount> transactionAccount = this.service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionAccount);
    }
    
    @PostMapping
    public ResponseEntity<TransactionAccount> create(@RequestBody TransactionAccount transactionAccount) {
    	this.service.create(transactionAccount);
        return ResponseEntity.status(HttpStatus.OK).body(transactionAccount);
    }
    
    @PutMapping
    public ResponseEntity<TransactionAccount> update(@RequestBody TransactionAccount transactionAccount) {
    	this.service.update(transactionAccount);
        return ResponseEntity.status(HttpStatus.OK).body(transactionAccount);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    	this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}