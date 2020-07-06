package com.acelera.tcc.group03.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionAccount;
import com.acelera.tcc.group03.requests.AccountBalanceRequest;
import com.acelera.tcc.group03.requests.AccountDepositRequest;
import com.acelera.tcc.group03.requests.AccountStatementRequest;
import com.acelera.tcc.group03.requests.AccountTransferRequest;
import com.acelera.tcc.group03.requests.AccountWithdrawRequest;
import com.acelera.tcc.group03.services.BankOperationService;

@RestController
@RequestMapping("/bank-operation")
public class BankOperationController {
    private BankOperationService bankOperationService;
    
    public BankOperationController(BankOperationService bankOperationService) {
        this.bankOperationService = bankOperationService;
    }
    
    @GetMapping("/get-account-balance")
    public ResponseEntity<Double> getAccountBalance(@RequestBody AccountBalanceRequest accountBalanceRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.getAccountBalance(accountBalanceRequest));
    }
    
    @GetMapping("/get-account-statement")
    public ResponseEntity<List<TransactionAccount>> getAccountStatement(@RequestBody AccountStatementRequest accountStatementRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.getAccountStatement(accountStatementRequest));
    }
    
    @PutMapping("/account-deposit")
    public ResponseEntity<CustomerAccount> accountDeposit(@RequestBody AccountDepositRequest accountDepositRequest) {
    	return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.accountDeposit(accountDepositRequest));
    }
    
    @PutMapping("/account-withdraw")
    public ResponseEntity<CustomerAccount> accountWithdraw(@RequestBody AccountWithdrawRequest accountWithdrawRequest) {
       	return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.accountWithdraw(accountWithdrawRequest));
    }
    
    @PutMapping("/account-transfer")
    public ResponseEntity<CustomerAccount> accountTransfer(@RequestBody AccountTransferRequest accountTransferRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bankOperationService.transferBetweenAccounts(accountTransferRequest));
    }
}