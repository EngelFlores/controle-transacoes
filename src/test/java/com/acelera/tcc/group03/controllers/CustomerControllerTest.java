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
import static org.mockito.Mockito.doNothing;

class CustomerControllerTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        this.customerService = Mockito.mock(CustomerService.class);
    }

    @Test
    void returnStatus200WhenGetCustomers(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Mockito.when(customerService.getAll()).thenReturn(Collections.singletonList(customer));

        CustomerController customerController = new CustomerController(customerService);
        ResponseEntity<List<Customer>> current = customerController.getAll();

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(customer)));

    }

    @Test
    void returnStatus200WhenGetOneCustomers(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        customer.setId(10L);
        Mockito.when(customerService.getById(customer.getId())).thenReturn(java.util.Optional.of(customer));

        CustomerController customerController = new CustomerController(customerService);
        ResponseEntity<Optional<Customer>> current = customerController.getCustomer(customer.getId());

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(java.util.Optional.of(customer)));

    }

    @Test
    void returnStatus204WhenPostCustomers(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Mockito.when(customerService.create(customer)).thenReturn(customer);

        CustomerController customerController = new CustomerController(customerService);
        ResponseEntity<Customer> current = customerController.create(customer);

        assertEquals(current, ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    }

    @Test
    void returnStatus204WhenDeleteCustomers(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        customerService.create(customer);
        customer.setId(12L);

        doNothing().when(customerService).delete(customer.getId());

        CustomerController customerController = new CustomerController(customerService);

        ResponseEntity<Void> status = customerController.deleteCostumer(customer.getId());
        Optional<Customer> customerDeleted = customerService.getById(customer.getId());

        assertEquals(status, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        assertNotEquals(customer, customerDeleted);

    }

}