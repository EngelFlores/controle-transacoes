package com.acelera.tcc.group03.services;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionAccount;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.exceptions.CustomerAccountInsufficientFundsForWithdraw;
import com.acelera.tcc.group03.exceptions.CustomerAccountNotFound;
import com.acelera.tcc.group03.exceptions.TransactionChannelNotFound;
import com.acelera.tcc.group03.exceptions.TransactionTypeNotFound;
import com.acelera.tcc.group03.requests.AccountBalanceRequest;
import com.acelera.tcc.group03.requests.AccountDepositRequest;
import com.acelera.tcc.group03.requests.AccountStatementRequest;
import com.acelera.tcc.group03.requests.AccountTransferRequest;
import com.acelera.tcc.group03.requests.AccountWithdrawRequest;

@Service
public class BankOperationService {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
    
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
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountBalanceRequest.getSourceAccountId());
		if (!optionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Account " + accountBalanceRequest.getSourceAccountId() + " not found.");
		}
		
		Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId());
		if (!optionalTransactionType.isPresent()) {
			throw new TransactionTypeNotFound("Transaction Type " + accountBalanceRequest.getTransactionTypeId() + " not found.");
		}
		
		Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId());
		if (!optionalTransactionChannel.isPresent()) {
			throw new TransactionChannelNotFound("Transaction Channel " + accountBalanceRequest.getTransactionChannelId() + " not found.");
		}
		
		CustomerAccount customerAccount = optionalCustomerAccount.get();
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
		
		return customerAccount.getAccountBalance();
	}
	
	public CustomerAccount accountDeposit(AccountDepositRequest accountDepositRequest) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountDepositRequest.getSourceAccountId());
		if (!optionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Account " + accountDepositRequest.getSourceAccountId() + " not found.");
		}
		
		Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountDepositRequest.getTransactionTypeId());
		if (!optionalTransactionType.isPresent()) {
			throw new TransactionTypeNotFound("Transaction Type " + accountDepositRequest.getTransactionTypeId() + " not found.");
		}
		
		Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountDepositRequest.getTransactionChannelId());
		if (!optionalTransactionChannel.isPresent()) {
			throw new TransactionChannelNotFound("Transaction Channel " + accountDepositRequest.getTransactionChannelId() + " not found.");
		}
		
		CustomerAccount customerAccount = optionalCustomerAccount.get();
		
		customerAccount.setAccountBalance(customerAccount.getAccountBalance() + accountDepositRequest.getAmountToDeposit());
		this.customerAccountService.update(customerAccount);
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
		
		return customerAccount;
	}
	
	public CustomerAccount accountWithdraw(AccountWithdrawRequest accountWithdrawRequest) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountWithdrawRequest.getSourceAccountId());
		if (!optionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Account " + accountWithdrawRequest.getSourceAccountId() + " not found.");
		}
		
		Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId());
		if (!optionalTransactionType.isPresent()) {
			throw new TransactionTypeNotFound("Transaction Type " + accountWithdrawRequest.getTransactionTypeId() + " not found.");
		}
		
		Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId());
		if (!optionalTransactionChannel.isPresent()) {
			throw new TransactionChannelNotFound("Transaction Channel " + accountWithdrawRequest.getTransactionChannelId() + " not found.");
		}
		
		CustomerAccount customerAccount = optionalCustomerAccount.get();
		
		if (customerAccount.getAccountBalance() < accountWithdrawRequest.getAmountToWithdraw()) {
			throw new CustomerAccountInsufficientFundsForWithdraw("Insufficient funds (" + BankOperationService.CURRENCY_FORMAT.format(customerAccount.getAccountBalance()) + ") in the Customer Account " + accountWithdrawRequest.getSourceAccountId() + " for the requested Withdraw (" + BankOperationService.CURRENCY_FORMAT.format(accountWithdrawRequest.getAmountToWithdraw()) + ").");
		}
		
		customerAccount.setAccountBalance(customerAccount.getAccountBalance() - accountWithdrawRequest.getAmountToWithdraw());
		this.customerAccountService.update(customerAccount);
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
		
		return customerAccount;
	}
	
	public CustomerAccount transferBetweenAccounts(AccountTransferRequest accountTransferRequest) {
		Optional<CustomerAccount> sourceOptionalCustomerAccount = this.customerAccountService.getById(accountTransferRequest.getSourceAccountId());
		if (!sourceOptionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Source Account " + accountTransferRequest.getSourceAccountId() + " not found.");
		}
		
		Optional<CustomerAccount> targetOptionalCustomerAccount = this.customerAccountService.getById(accountTransferRequest.getTargetAccountId());
		if (!targetOptionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Target Account " + accountTransferRequest.getTargetAccountId() + " not found.");
		}
		
		Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountTransferRequest.getTransactionTypeId());
		if (!optionalTransactionType.isPresent()) {
			throw new TransactionTypeNotFound("Transaction Type " + accountTransferRequest.getTransactionTypeId() + " not found.");
		}
		
		Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountTransferRequest.getTransactionChannelId());
		if (!optionalTransactionChannel.isPresent()) {
			throw new TransactionChannelNotFound("Transaction Channel " + accountTransferRequest.getTransactionChannelId() + " not found.");
		}
		
		CustomerAccount sourceCustomerAccount = sourceOptionalCustomerAccount.get();
		CustomerAccount targetCustomerAccount = targetOptionalCustomerAccount.get();
		
		if (sourceCustomerAccount.getAccountBalance() < accountTransferRequest.getAmountToTransfer()) {
			throw new CustomerAccountInsufficientFundsForWithdraw("Insufficient funds (" + BankOperationService.CURRENCY_FORMAT.format(sourceCustomerAccount.getAccountBalance()) + ") in the Customer Account " + accountTransferRequest.getSourceAccountId() + " for the requested Withdraw (" + BankOperationService.CURRENCY_FORMAT.format(accountTransferRequest.getAmountToTransfer()) + ").");
		}
		
		sourceCustomerAccount.setAccountBalance(sourceCustomerAccount.getAccountBalance() - accountTransferRequest.getAmountToTransfer());
		this.customerAccountService.update(sourceCustomerAccount);
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),sourceCustomerAccount,sourceCustomerAccount.getAccountBalance());
		
		targetCustomerAccount.setAccountBalance(targetCustomerAccount.getAccountBalance() + accountTransferRequest.getAmountToTransfer());
		this.customerAccountService.update(targetCustomerAccount);
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),targetCustomerAccount,targetCustomerAccount.getAccountBalance());
		
		return sourceCustomerAccount;
	}
	
	public List<TransactionAccount> getAccountStatement(AccountStatementRequest accountStatementRequest) {
		Optional<CustomerAccount> optionalCustomerAccount = this.customerAccountService.getById(accountStatementRequest.getSourceAccountId());
		if (!optionalCustomerAccount.isPresent()) {
			throw new CustomerAccountNotFound("Account " + accountStatementRequest.getSourceAccountId() + " not found.");
		}
		
		Optional<TransactionType> optionalTransactionType = this.transactionTypeService.getById(accountStatementRequest.getTransactionTypeId());
		if (!optionalTransactionType.isPresent()) {
			throw new TransactionTypeNotFound("Transaction Type " + accountStatementRequest.getTransactionTypeId() + " not found.");
		}
		
		Optional<TransactionChannel> optionalTransactionChannel = this.transactionChannelService.getById(accountStatementRequest.getTransactionChannelId());
		if (!optionalTransactionChannel.isPresent()) {
			throw new TransactionChannelNotFound("Transaction Channel " + accountStatementRequest.getTransactionChannelId() + " not found.");
		}
		
		CustomerAccount customerAccount = optionalCustomerAccount.get();
		
		List<TransactionAccount> transactionAccounts = this.transactionAccountService.getForStatement(customerAccount,accountStatementRequest.getAmountOfDays());
		
		this.registerTransactionAccount(optionalTransactionType.get(),optionalTransactionChannel.get(),customerAccount,customerAccount.getAccountBalance());
		
		return transactionAccounts;
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
}