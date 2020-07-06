package com.acelera.tcc.group03.services;

import com.acelera.tcc.group03.domains.*;
import com.acelera.tcc.group03.repositories.CustomerAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerAccountServiceTest {
    private CustomerAccountRepository customerAccountRepository;

    @BeforeEach
    public void setUp(){
        this.customerAccountRepository = Mockito.mock(CustomerAccountRepository.class);
    }

    @Test
    void saveCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Bank bank = new Bank("Bank1","12345");
        Agency agency = new Agency("Agency1","123456", bank);

        CustomerAccount expected = new CustomerAccount(customer,agency, 1000.0);

        Mockito.when(customerAccountRepository.save(expected)).thenReturn(expected);

        CustomerAccountService customerAccountService = new CustomerAccountService(customerAccountRepository);
        CustomerAccount current = customerAccountService.create(expected);

        assertEquals(expected, current);
        Mockito.verify(customerAccountRepository,Mockito.times(1)).save(expected);
    }

    @Test
    void failsToSaveCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Bank bank = new Bank("Bank1","12345");

        CustomerAccount expected = new CustomerAccount(customer,null, 1000.0);

        CustomerAccountService customerAccountService = new CustomerAccountService(customerAccountRepository);

        Assertions.assertThrows(NullPointerException.class,()->{
            customerAccountService.create(expected);
        });
        Mockito.verifyNoInteractions(customerAccountRepository);
    }
}
