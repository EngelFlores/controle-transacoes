package com.acelera.tcc.group03.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.domains.TransactionTypeAction;
import com.acelera.tcc.group03.services.TransactionTypeService;

public class TransactionTypeControllerTest {
    private TransactionTypeService transactionTypeService = Mockito.mock(TransactionTypeService.class);
    private TransactionTypeController transactionTypeController = new TransactionTypeController(transactionTypeService);

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

    @Test
    public void mustReturnTransactionTypeFromController() {
        // Input
    	Long transactionTypeId = 1L;
    	TransactionType transactionType = new TransactionType();
    	transactionType.setName("Cash Deposit");
    	transactionType.setAction(TransactionTypeAction.CREDIT);

        // Mock
        when(transactionTypeService.getById(transactionTypeId)).thenReturn(Optional.of(transactionType));

        TransactionTypeController transactionTypeController = new TransactionTypeController(this.transactionTypeService);
        ResponseEntity<Optional<TransactionType>> current = transactionTypeController.getById(transactionTypeId);

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Optional.of(transactionType)));
    }
}