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
        Long customerAccountId = 15L;
        Double accountBalance = 1000.0;
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Bank bank = new Bank("Bank1","12345");
        Agency agency = new Agency("Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, accountBalance);

        Mockito.when(customerAccountService.getById(customerAccountId)).thenReturn(Optional.of(customerAccount));

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<Optional<CustomerAccount>> current = customerAccountController.getCustomerAccount(customerAccountId);

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(java.util.Optional.of(customerAccount)));

    }

    @Test
    void returnStatus204WhenPostCustomerAccount(){
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Bank bank = new Bank("Bank1","12345");
        Agency agency = new Agency("Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, 1000.0);
        Mockito.when(customerAccountService.create(customerAccount)).thenReturn(customerAccount);

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<CustomerAccount> current = customerAccountController.create(customerAccount);

        assertEquals(current, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @Test
    void returnStatus204WhenDeleteCustomerAccount(){
        Long customerAccountId = 15L;
        Customer customer = new Customer("Joao", CustomerType.INDIVIDUAL, "123456");
        Bank bank = new Bank("Bank1","12345");
        Agency agency = new Agency("Agency1","123456", bank);

        CustomerAccount customerAccount = new CustomerAccount(customer,agency, 1000.0);

        doNothing().when(customerAccountService).delete(customerAccountId);

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);

        ResponseEntity<Void> status = customerAccountController.deleteCostumerAccount(customerAccountId);
        Optional<CustomerAccount> accountDeleted = customerAccountService.getById(customerAccountId);

        assertEquals(status, ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        assertNotEquals(customer, accountDeleted);

    }
}
