package com.acelera.tcc.group03.services;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.repositories.CustomerAccountRepository;

import java.util.Optional;

public class CustomerAccountService {
    private CustomerAccountRepository customerAccountRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public CustomerAccount create(CustomerAccount customerAccount){
        if(customerAccount.getCustomer() == null || customerAccount.getAccountBalance() == null || customerAccount.getAgency() == null){
            throw new NullPointerException();
        }
        return customerAccountRepository.save(customerAccount);
    }

    public CustomerAccount update(CustomerAccount customerAccount){
        return customerAccountRepository.save(customerAccount);
    }

    public Optional<CustomerAccount> getById (Long id){
        return customerAccountRepository.findById(id);
    }
    public void delete(Long id) {
        customerAccountRepository.deleteById(id);
    }
}
