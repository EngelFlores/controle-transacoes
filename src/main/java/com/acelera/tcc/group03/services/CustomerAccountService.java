package com.acelera.tcc.group03.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.repositories.CustomerAccountRepository;

@Service
public class CustomerAccountService {
    private CustomerAccountRepository repository;
    
    public CustomerAccountService(CustomerAccountRepository repository) {
        this.repository = repository;
    }
    
	public List<CustomerAccount> getAll() {
		return this.repository.findAll();
	}
    
    public Optional<CustomerAccount> getById(Long id) {
        return this.repository.findById(id);
    }
    
    public CustomerAccount create(CustomerAccount customerAccount) {
        if (customerAccount.getCustomer() == null || customerAccount.getAccountBalance() == null || customerAccount.getAgency() == null) {
            throw new NullPointerException();
        }
        
        return this.repository.save(customerAccount);
    }
    
    public CustomerAccount update(CustomerAccount customerAccount) {
        return this.repository.save(customerAccount);
    }
    
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}