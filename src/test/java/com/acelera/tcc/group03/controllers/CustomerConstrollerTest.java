package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerType;
import com.acelera.tcc.group03.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConstrollerTest {
    private CustomerService customerService;
    
    @BeforeEach
    void setUp() {
        this.customerService = Mockito.mock(CustomerService.class);
    }
    
    @Test
    void returnStatus200WhenGetCustomers() {
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        
        Mockito.when(this.customerService.getAll()).thenReturn(Collections.singletonList(customer));
        
        CustomerConstroller customerConstroller = new CustomerConstroller(this.customerService);
        ResponseEntity<List<Customer>> current = customerConstroller.getAll();
        
        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(customer)));
    }
    
    @Test
    void returnStatus200WhenGetOneCustomers(){
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        Mockito.when(this.customerService.getById(customerId)).thenReturn(java.util.Optional.of(customer));
        
        CustomerConstroller customerConstroller = new CustomerConstroller(this.customerService);
        ResponseEntity<Optional<Customer>> current = customerConstroller.getCustomer(customerId);
        
        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(java.util.Optional.of(customer)));
    }
    
    @Test
    void returnStatus201WhenPostCustomers(){
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        
        Mockito.when(this.customerService.create(customer)).thenReturn(customer);
        
        CustomerConstroller customerConstroller = new CustomerConstroller(this.customerService);
        ResponseEntity<Customer> current = customerConstroller.create(customer);
        
        assertEquals(current, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    
    /*
    @Test
    void returnStatus201WhenDeleteCustomers(){
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        
        Mockito.when(this.customerService.getById(customerId)).thenReturn(java.util.Optional.of(customer));
        customerService.delete(customer.getId());
        
        CustomerConstroller customerConstroller = new CustomerConstroller(this.customerService);
        ResponseEntity<Void> current = customerConstroller.deleteCostumer(customerId);
        
        assertEquals(current, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    */
}