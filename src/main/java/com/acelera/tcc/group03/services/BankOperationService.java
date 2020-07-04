package com.acelera.tcc.group03.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionAccount;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.exceptions.CustomerAccountNotFound;
import com.acelera.tcc.group03.requests.AccountBalanceRequest;
import com.acelera.tcc.group03.requests.AccountDepositRequest;

@Service
public class BankOperationService {
	private TransactionTypeService transactionTypeService;
	private TransactionChannelService transactionChannelService;
	private CustomerAccountService customerAccountService;
	private TransactionAccountService transactionAccountService;
	
	public BankOperationService(
			TransactionTypeService transactionTypeService
			,TransactionChannelService transactionChannelService
			,CustomerAccountService customerAccountService
			,TransactionAccountService transactionAccountService
	) {
		this.transactionTypeService = transactionTypeService;
		this.transactionChannelService = transactionChannelService;
		this.customerAccountService = customerAccountService;
		this.transactionAccountService = transactionAccountService;
	}
	
	public Double getAccountBalance(AccountBalanceRequest accountBalanceRequest) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountBalanceRequest.getAccountId());
		
		if (optionalCustomerAccount.isPresent()) {
			CustomerAccount customerAccount = optionalCustomerAccount.get();
			
			Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId());
			Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId());
			
			this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
			
			return customerAccount.getAccountBalance();
		} else {
			throw new CustomerAccountNotFound();
		}
	}
	
	public CustomerAccount accountDeposit(AccountDepositRequest accountDepositRequest) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountDepositRequest.getAccountId());
		
		if (optionalCustomerAccount.isPresent()) {
			CustomerAccount customerAccount = optionalCustomerAccount.get();
			customerAccount.setAccountBalance(customerAccount.getAccountBalance() + accountDepositRequest.getAmountToDeposit());
			
			Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountDepositRequest.getTransactionTypeId());
			Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountDepositRequest.getTransactionChannelId());
			
			this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
			
			this.customerAccountService.update(customerAccount);
			
			return customerAccount;
		}
		
		return null;
	}
	
	private void registerTransactionAccount(TransactionType transactionType, TransactionChannel transactionChannel, CustomerAccount customerAccount, Double amount) {
		TransactionAccount transactionAccount = new TransactionAccount();
		
		transactionAccount.setTransactionType(transactionType);
		transactionAccount.setTransactionChannel(transactionChannel);
		transactionAccount.setCustomerAccount(customerAccount);
		transactionAccount.setAmount(amount);
		transactionAccount.setTransactionMoment(LocalDateTime.now());
		
		this.transactionAccountService.create(transactionAccount);
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