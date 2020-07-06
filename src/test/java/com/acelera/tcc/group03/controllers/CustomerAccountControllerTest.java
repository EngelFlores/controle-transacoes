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
        Customer customer = new Customer();
        customer.setName("Joao");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        Bank bank = new Bank();
        bank.setName("Bank1");
        bank.setNumber("12345");
        Agency agency = new Agency();
        agency.setName("Agency1");
        agency.setNumber("123456");
        agency.setBank(bank);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAgency(agency);
        customerAccount.setAccountBalance(1000.0);

        Mockito.when(customerAccountService.getById(customerAccountId)).thenReturn(Optional.of(customerAccount));

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<Optional<CustomerAccount>> current = customerAccountController.getCustomerAccount(customerAccountId);

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(Optional.of(customerAccount)));

    }

    @Test
    void returnStatus200WhenPostCustomerAccount(){
        Customer customer = new Customer();
        customer.setName("Joao");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        Bank bank = new Bank();
        bank.setName("Bank1");
        bank.setNumber("12345");
        Agency agency = new Agency();
        agency.setName("Agency1");
        agency.setNumber("123456");
        agency.setBank(bank);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAgency(agency);
        customerAccount.setAccountBalance(1000.0);
        Mockito.when(customerAccountService.create(customerAccount)).thenReturn(customerAccount);

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);
        ResponseEntity<CustomerAccount> current = customerAccountController.create(customerAccount);

        assertEquals(current, ResponseEntity.status(HttpStatus.OK).body(customerAccount));
    }

    @Test
    void returnStatus200WhenDeleteCustomerAccount(){
        Long customerAccountId = 15L;
        Customer customer = new Customer();
        customer.setName("Joao");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setTin("123456");
        Bank bank = new Bank();
        bank.setName("Bank1");
        bank.setNumber("12345");
        Agency agency = new Agency();
        agency.setName("Agency1");
        agency.setNumber("123456");
        agency.setBank(bank);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAgency(agency);
        customerAccount.setAccountBalance(1000.0);

        doNothing().when(customerAccountService).delete(customerAccountId);

        CustomerAccountController customerAccountController = new CustomerAccountController(customerAccountService);

        ResponseEntity<Void> status = customerAccountController.deleteCostumerAccount(customerAccountId);
        Optional<CustomerAccount> accountDeleted = customerAccountService.getById(customerAccountId);

        assertEquals(status, ResponseEntity.status(HttpStatus.OK).build());
        assertNotEquals(customerAccount, accountDeleted);

    }
}
