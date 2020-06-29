package com.acelera.tcc.group03.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerType;
import com.acelera.tcc.group03.repositories.CustomerRepository;
import com.acelera.tcc.group03.services.CustomerService;

class CustomerServiceTest {
    private CustomerRepository customerRepository;
    
    @BeforeEach
    public void setUp(){
        this.customerRepository = Mockito.mock(CustomerRepository.class);
    }
    
    @Test
    void saveCustomer(){
        Customer expected = new Customer();
        expected.setName("Pedro");
        expected.setType(CustomerType.INDIVIDUAL);
        expected.setTin("123456");
        Mockito.when(customerRepository.save(expected)).thenReturn(expected);
        
        CustomerService customerService = new CustomerService(customerRepository);
        Customer current = customerService.create(expected);
        
        assertEquals(expected, current);
        Mockito.verify(customerRepository,Mockito.times(1)).save(expected);
    }
    
    @Test
    void failsToSaveCustomer(){
        Customer expected = new Customer();
        expected.setName(null);
        expected.setType(CustomerType.INDIVIDUAL);
        expected.setTin(null);
        CustomerService customerService = new CustomerService(customerRepository);

        Assertions.assertThrows(NullPointerException.class,()->{
            customerService.create(expected);
        });
        Mockito.verifyNoInteractions(customerRepository);
    }
}