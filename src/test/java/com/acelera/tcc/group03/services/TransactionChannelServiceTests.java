package com.acelera.tcc.group03.services;

import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.repositories.TransactionChannelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionChannelServiceTests {
    @Mock
    private TransactionChannelRepository transactionChannelRepository;
    
    @InjectMocks
    private TransactionChannelService transactionChannelService;
    
    @Test
    void shouldSaveWithSuccessTransactionChannel(){
        TransactionChannel expected = new TransactionChannel();
        expected.setName("Test");
        
        Mockito.when(transactionChannelRepository.save(expected)).thenReturn(expected);
        
        TransactionChannel actual = transactionChannelService.create(expected);
        
        assertEquals(expected, actual);
        Mockito.verify(transactionChannelRepository, Mockito.times(1)).save(expected);
    }
    
    @Test
    void shouldFailToSaveTransactionChannelWithNoName(){
        TransactionChannel expected = new TransactionChannel();

        Assertions.assertThrows(NullPointerException.class, () -> {
            transactionChannelService.create(expected);
        });
        Mockito.verifyNoInteractions(transactionChannelRepository);
    }
}