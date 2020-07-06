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

        CustomerAccount expected = new CustomerAccount();
        expected.setCustomer(customer);
        expected.setAgency(agency);
        expected.setAccountBalance(1000.0);

        Mockito.when(customerAccountRepository.save(expected)).thenReturn(expected);

        CustomerAccountService customerAccountService = new CustomerAccountService(customerAccountRepository);
        CustomerAccount current = customerAccountService.create(expected);

        assertEquals(expected, current);
        Mockito.verify(customerAccountRepository,Mockito.times(1)).save(expected);
    }

    @Test
    void failsToSaveCustomerAccount(){
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

        CustomerAccount expected = new CustomerAccount();
        expected.setCustomer(customer);
        expected.setAgency(null);
        expected.setAccountBalance(1000.0);

        CustomerAccountService customerAccountService = new CustomerAccountService(customerAccountRepository);

        Assertions.assertThrows(NullPointerException.class,()->{
            customerAccountService.create(expected);
        });
        Mockito.verifyNoInteractions(customerAccountRepository);
    }
}
