package com.acelera.tcc.group03.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.CustomerAccount;

@Service
public class BankOperationService {
	private CustomerAccountService customerAccountService;
	
    public BankOperationService(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }
	
	public Double getAccountBalance(Long accountId) {
        Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountId);
		
		if (optionalCustomerAccount.isPresent()) {
			return optionalCustomerAccount.get().getAccountBalance();
		}
		
		return null;
	}
    
	public CustomerAccount accountDeposit(Long accountId, Double amount) {
        Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountId);
        
		if (optionalCustomerAccount.isPresent()) {
			CustomerAccount customerAccount = optionalCustomerAccount.get();
			customerAccount.setAccountBalance(customerAccount.getAccountBalance() + amount);
			this.customerAccountService.update(customerAccount);
			return customerAccount;
		}
		
		return null;
	}
}