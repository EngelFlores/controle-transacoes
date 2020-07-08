package com.acelera.tcc.group03.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.services.BankService;

public class BankControllerTest {
    private BankService bankService;
    
    @BeforeEach
    void setUp() {
        this.bankService = Mockito.mock(BankService.class);
    }
    
    @Test
    void returnStatus200WhenGetBanks() {
        Bank bank = new Bank();
        bank.setName("banco de teste");
        bank.setNumber("123456");
        Mockito.when(bankService.getAll()).thenReturn(Collections.singletonList(bank));
        
        BankController bankController = new BankController(bankService);
        ResponseEntity<List<Bank>> current = bankController.getAll();
        
        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(bank)));
    }
    
    @Test
    void returnStatus200WhenGetOneBank() {
        Long bankId = 10L;
        Bank bank = new Bank();
        bank.setName("banco de teste");
        bank.setNumber("123456");
        Mockito.when(bankService.getById(bankId)).thenReturn(Optional.of(bank));
        
        BankController bankController = new BankController(bankService);
        ResponseEntity<Optional<Bank>> current = bankController.getBank(bankId);
        
        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Optional.of(bank)));
    }
    
    @Test
    void shouldReturn200WhenDeleteBank() {
        Long bankId = 1L;
        Bank bank = new Bank();
        bank.setName("banco de teste");
        bank.setNumber("123456");
        bankService.create(bank);
        
        Mockito.when(bankService.getById(bankId)).thenReturn(Optional.of(bank));
        bankService.delete(bankId);
        
        BankController bankController = new BankController(bankService);
        ResponseEntity<Void> actual = bankController.delete(bankId);
        
        assertEquals(actual, ResponseEntity.status(HttpStatus.OK).build());
    }
}