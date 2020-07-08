package com.acelera.tcc.group03.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.repositories.BankRepository;

public class BankServiceTest {
    private BankRepository bankRepository;
    
    @BeforeEach
    public void setUp(){
        this.bankRepository = Mockito.mock(BankRepository.class);
    }
    
    @Test
    void saveBank() {
        Bank expected = new Bank();
        expected.setName("banco de teste");
        expected.setNumber("123456");
        Mockito.when(bankRepository.save(expected)).thenReturn(expected);
        
        BankService bankService = new BankService(bankRepository);
        Bank current = bankService.create(expected);
        
        assertEquals(expected, current);
        Mockito.verify(bankRepository,Mockito.times(1)).save(expected);
    }
}