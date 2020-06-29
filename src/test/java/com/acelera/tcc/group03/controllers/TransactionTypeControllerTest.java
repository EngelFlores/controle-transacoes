package com.acelera.tcc.group03.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.domains.TransactionTypeAction;
import com.acelera.tcc.group03.services.TransactionTypeService;

public class TransactionTypeControllerTest {
    private TransactionTypeService transactionTypeService = Mockito.mock(TransactionTypeService.class);
    private TransactionTypeController transactionTypeController = new TransactionTypeController(transactionTypeService);
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void mustReturnTransactionTypeListFromController() {
        // Input
    	TransactionType transactionType01 = new TransactionType();
    	transactionType01.setName("Cash Deposit");
    	transactionType01.setAction(TransactionTypeAction.CREDIT);
    	
    	TransactionType transactionType02 = new TransactionType();
    	transactionType02.setName("Cash Withdraw");
    	transactionType02.setAction(TransactionTypeAction.DEBIT);
        
    	List<TransactionType> transactionTypeList = Arrays.asList(transactionType01, transactionType02);
        ResponseEntity<List<TransactionType>> expected = ResponseEntity.ok(transactionTypeList);
        
        // Mock
        when(transactionTypeService.getAll()).thenReturn(transactionTypeList);
        
        // Execution
        ResponseEntity<List<TransactionType>> actual = transactionTypeController.getAll();
        
        // Validation
        assertEquals(expected.getBody(), actual.getBody());
    }
    
    /*
    @Test
    public void mustReturnSpecificTransactionType() {
        // Input
        Long transactionTypeId = 1L;
    	TransactionType transactionType = new TransactionType();
    	transactionType.setName("Cash Deposit");
    	transactionType.setAction(TransactionTypeAction.CREDIT);
        
        // Mock
        when(transactionTypeService.getById(transactionTypeId)).thenReturn(Optional.of(transactionType));
        
        // Execution
        ResponseEntity<Optional<TransactionType>> actual = transactionTypeController.getById(transactionTypeId);
        
        // Validation
        assertEquals(transactionTypeId, actual.getBody().get().getId());
    }
    */
}