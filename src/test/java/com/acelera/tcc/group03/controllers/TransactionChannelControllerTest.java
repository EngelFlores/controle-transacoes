package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerType;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.services.TransactionChannelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionChannelControllerTest {
    private TransactionChannelService transactionChannelService = Mockito.mock(TransactionChannelService.class);
    
    @Test
    void shouldReturnStatus200WhenGetAllTransactionChannels(){
        TransactionChannel transactionChannel = new TransactionChannel("Test");
        
        Mockito.when(transactionChannelService.getAll()).thenReturn(Collections.singletonList(transactionChannel));
        
        TransactionChannelController transactionChannelController = new TransactionChannelController(transactionChannelService);
        ResponseEntity<List<TransactionChannel>> actual = transactionChannelController.getAll();
        
        assertEquals(actual, ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(transactionChannel)));
    }

    
    @Test
    void shouldReturnStatus200WhenGetTransactionChannelById(){
        Long transactionChannelId = 1L;
        TransactionChannel transactionChannel = new TransactionChannel("Test");
        Mockito.when(transactionChannelService.getById(transactionChannelId)).thenReturn(Optional.of(transactionChannel));
        
        TransactionChannelController transactionChannelController = new TransactionChannelController(transactionChannelService);
        ResponseEntity<Optional<TransactionChannel>> actual = transactionChannelController.getById(transactionChannelId);
        
        assertEquals(actual, ResponseEntity.status(HttpStatus.OK).body(Optional.of(transactionChannel)));
    }
    
    @Test
    void shouldReturn204WhenDeleteTransactionChannel(){
        Long transactionChannelId = 1L;
        TransactionChannel transactionChannel = new TransactionChannel("Test");
        transactionChannelService.create(transactionChannel);
        
        Mockito.when(transactionChannelService.getById(transactionChannelId)).thenReturn(Optional.of(transactionChannel));
        transactionChannelService.delete(transactionChannelId);
        
        TransactionChannelController transactionChannelController = new TransactionChannelController(transactionChannelService);
        ResponseEntity<Object> actual = transactionChannelController.delete(transactionChannelId);
        
        assertEquals(actual, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    
    /*
    @Test
    void shouldReturn201WhenCreateNewTransactionChannel(){
        TransactionChannel transactionChannel = new TransactionChannel("Test");
        Mockito.when(transactionChannelService.create(transactionChannel)).thenReturn(transactionChannel);
        
        TransactionChannelController transactionChannelController = new TransactionChannelController(transactionChannelService);
        ResponseEntity<TransactionChannel> actual = transactionChannelController.create(transactionChannel);
        
        assertEquals(actual, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    */
}