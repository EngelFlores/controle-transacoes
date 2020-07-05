package com.acelera.tcc.group03.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.services.BankOperationService;

@RestController
@RequestMapping("/bank-operation")
public class BankOperationController {
    private BankOperationService bankOperationService;
    
    public BankOperationController(BankOperationService bankOperationService) {
        this.bankOperationService = bankOperationService;
    }
    
    @GetMapping("/get-account-balance/{id}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable("id") Long accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.getAccountBalance(accountId));
    }
    
    @PutMapping("/account-deposit/{id}/{amount}")
    public ResponseEntity<CustomerAccount> accountDeposit(@PathVariable("id") Long accountId, @PathVariable("amount") Double amount) {
    	return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.accountDeposit(accountId,amount));
    }
    
    @PutMapping("/account-withdraw/{id}/{amount}")
    public ResponseEntity<CustomerAccount> accountWithdraw(@PathVariable("id") Long accountId, @PathVariable("amount") Double amount) {
    	return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.accountWithdraw(accountId,amount));
    }    
    
    @PutMapping("/account-transfer/{id-source-account}/{id-target-account}/{amount}")
    public ResponseEntity<CustomerAccount> accountTransfer(@PathVariable("id-source-account") Long sourceAccountId, @PathVariable("id-target-account") Long targetAccountId, @PathVariable("amount") Double amount) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.transferBetweenAccounts(sourceAccountId,targetAccountId, amount ));
    }
    
    /*
    @GetMapping("/get-account-statement/{id}/{ini}/{end}")
    public ResponseEntity<Optional<Double>> getAccountStatement(@PathVariable("id") Long accountId) {
    }
    */
}