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
	
	public CustomerAccount accountWithdraw(Long accountId, Double amount) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountId);
		
		if (optionalCustomerAccount.isPresent()) {
			CustomerAccount customerAccount = optionalCustomerAccount.get();
			
			if (customerAccount.getAccountBalance() >= amount) {
				customerAccount.setAccountBalance(customerAccount.getAccountBalance() - amount);
				this.customerAccountService.update(customerAccount);
				return customerAccount;
			}
		}
		
		return null;
	}
	
	public CustomerAccount transferBetweenAccounts(Long sourceAccountId, Long targetAccountId, Double amount) {
		Optional<CustomerAccount> sourceOptionalCustomerAccount = this.customerAccountService.getById(sourceAccountId);
		Optional<CustomerAccount> targetOptionalCustomerAccount = this.customerAccountService.getById(targetAccountId);
		
		if (sourceOptionalCustomerAccount.isPresent() && targetOptionalCustomerAccount.isPresent()) {
			CustomerAccount sourceCustomerAccount = sourceOptionalCustomerAccount.get();
			CustomerAccount targetCustomerAccount = targetOptionalCustomerAccount.get();
			
			if (sourceCustomerAccount.getAccountBalance() >= amount) {
				sourceCustomerAccount.setAccountBalance(sourceCustomerAccount.getAccountBalance() - amount);
				this.customerAccountService.update(sourceCustomerAccount);
				targetCustomerAccount.setAccountBalance(targetCustomerAccount.getAccountBalance() + amount);
				this.customerAccountService.update(targetCustomerAccount);
				
				return sourceCustomerAccount;
			}
		}
		
		return null;
	}
}