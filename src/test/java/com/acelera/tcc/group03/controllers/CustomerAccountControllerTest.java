package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.*;
import com.acelera.tcc.group03.services.CustomerAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

public class CustomerAccountControllerTest {
    private CustomerAccountService customerAccountService;

    @BeforeEach
    void setUp(){
        this.customerAccountService = Mockito.mock(CustomerAccountService.class);
    }

    @Test
    void returnStatus200WhenGetCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        customer.setId(10L);
        Bank bank = new Bank(100L,"Bank1","12345");
        Agency agency = new Agency(102L,"Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, 1000.0);
        customerAccount.setId(15L);

        Mockito.when(customerAccountService.getById(customerAccount.getId())).thenReturn(java.util.Optional.of(customerAccount));

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<Optional<CustomerAccount>> current = customerAccountController.getCustomerAccount(customerAccount.getId());

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(java.util.Optional.of(customerAccount)));

    }

    @Test
    void returnStatus204WhenPostCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        customer.setId(10L);
        Bank bank = new Bank(100L,"Bank1","12345");
        Agency agency = new Agency(102L,"Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, 1000.0);
        customerAccount.setId(15L);
        Mockito.when(customerAccountService.create(customerAccount)).thenReturn(customerAccount);

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<CustomerAccount> current = customerAccountController.create(customerAccount);

        assertEquals(current, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @Test
    void returnStatus204WhenDeleteCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        customer.setId(10L);
        Bank bank = new Bank(100L,"Bank1","12345");
        Agency agency = new Agency(102L,"Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, 1000.0);
        customerAccount.setId(15L);

        doNothing().when(customerAccountService).delete(customer.getId());

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);

        ResponseEntity<Void> status = customerAccountController.deleteCostumerAccount(customerAccount.getId());
        Optional<CustomerAccount> accountDeleted = customerAccountService.getById(customer.getId());

        assertEquals(status, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        assertNotEquals(customer, accountDeleted);

    }
}
