package com.acelera.tcc.group03.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.acelera.tcc.group03.domains.Agency;
import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.CustomerType;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.domains.TransactionTypeAction;
import com.acelera.tcc.group03.exceptions.CustomerAccountInsufficientFundsForWithdraw;
import com.acelera.tcc.group03.exceptions.CustomerAccountNotFound;
import com.acelera.tcc.group03.exceptions.TransactionChannelNotFound;
import com.acelera.tcc.group03.exceptions.TransactionTypeNotFound;
import com.acelera.tcc.group03.repositories.CustomerAccountRepository;
import com.acelera.tcc.group03.repositories.TransactionAccountRepository;
import com.acelera.tcc.group03.repositories.TransactionChannelRepository;
import com.acelera.tcc.group03.repositories.TransactionTypeRepository;
import com.acelera.tcc.group03.requests.AccountBalanceRequest;
import com.acelera.tcc.group03.requests.AccountDepositRequest;
import com.acelera.tcc.group03.requests.AccountTransferRequest;
import com.acelera.tcc.group03.requests.AccountWithdrawRequest;

public class BankOperationServiceTest {
	private BankOperationService bankOperationService;
	
    private TransactionTypeService transactionTypeService;
	private TransactionChannelService transactionChannelService;
    private CustomerAccountService customerAccountService;
    private TransactionAccountService transactionAccountService;
    
    public BankOperationServiceTest() {
        this.transactionTypeService = new TransactionTypeService(Mockito.mock(TransactionTypeRepository.class));
        this.transactionChannelService = new TransactionChannelService(Mockito.mock(TransactionChannelRepository.class));
        this.customerAccountService = new CustomerAccountService(Mockito.mock(CustomerAccountRepository.class));
        this.transactionAccountService = new TransactionAccountService(Mockito.mock(TransactionAccountRepository.class));
        
    	this.bankOperationService = new BankOperationService(this.transactionTypeService
    														,this.transactionChannelService
    														,this.customerAccountService
    														,this.transactionAccountService);
    }
    
    private AccountBalanceRequest setAccountBalanceRequest() {
    	Long accountId = 1L;
    	Long transactionTypeId = 1L;
    	Long transactionChannelId = 1L;
    	
    	AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
    	accountBalanceRequest.setSourceAccountId(accountId);
    	accountBalanceRequest.setTransactionTypeId(transactionTypeId);
    	accountBalanceRequest.setTransactionChannelId(transactionChannelId);
    	
    	return accountBalanceRequest;
    }
    
    private CustomerAccount getCustomerAccount(Double balance) {
    	Bank bank = new Bank();
    	bank.setName("Banrisul");
    	bank.setNumber("041");
    	Agency agency = new Agency();
    	agency.setBank(bank);
    	agency.setName("Central");
    	agency.setNumber("0001");
    	Customer customer = new Customer();
    	customer.setName("Pedro Álvarez Cabral");
    	customer.setType(CustomerType.INDIVIDUAL);
    	customer.setTin("123456789");
    	CustomerAccount customerAccount = new CustomerAccount();
    	customerAccount.setAgency(agency);
    	customerAccount.setCustomer(customer);
    	customerAccount.setAccountBalance(balance);
    	return customerAccount;
    }
    
    private TransactionType getTransactionType() {
    	TransactionType transactionType = new TransactionType();
    	transactionType.setName("Statement");
    	transactionType.setAction(TransactionTypeAction.NONE);
    	return transactionType;
    }
    
    private TransactionChannel getTransactionChannel() {
    	TransactionChannel transactionChannel = new TransactionChannel();
    	transactionChannel.setName("Internet");
    	return transactionChannel;
    }
    
	@Test
	public void getAccountBalanceCustomerAccountNotFound() {
		AccountBalanceRequest accountBalanceRequest = this.setAccountBalanceRequest();
		
		Mockito.when(customerAccountService.getById(accountBalanceRequest.getSourceAccountId())).thenReturn(Optional.empty());
    	Mockito.when(transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountNotFound.class, () -> bankOperationService.getAccountBalance(accountBalanceRequest));
	}
	
	@Test
	public void getAccountBalanceTransactionTypeNotFound() {
		AccountBalanceRequest accountBalanceRequest = this.setAccountBalanceRequest();
    	
		Mockito.when(customerAccountService.getById(accountBalanceRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(0.0)));
    	Mockito.when(transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId())).thenReturn(Optional.empty());
    	Mockito.when(transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(TransactionTypeNotFound.class, () -> bankOperationService.getAccountBalance(accountBalanceRequest));
	}
	
	@Test
	public void getAccountBalanceTransactionChannelNotFound() {
		AccountBalanceRequest accountBalanceRequest = this.setAccountBalanceRequest();
    	
		Mockito.when(customerAccountService.getById(accountBalanceRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(0.0)));
    	Mockito.when(transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId())).thenReturn(Optional.empty());
    	
    	assertThrows(TransactionChannelNotFound.class, () -> bankOperationService.getAccountBalance(accountBalanceRequest));
	}
	
	@Test
	public void getAccountBalanceSucess() {
		CustomerAccount customerAccount = this.getCustomerAccount(50.0);
		
		AccountBalanceRequest accountBalanceRequest = this.setAccountBalanceRequest();
    	
		Mockito.when(customerAccountService.getById(accountBalanceRequest.getSourceAccountId())).thenReturn(Optional.of(customerAccount));
    	Mockito.when(transactionTypeService.getById(accountBalanceRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountBalanceRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	Double accountBalance = bankOperationService.getAccountBalance(accountBalanceRequest);
    	
    	assertEquals(customerAccount.getAccountBalance(),accountBalance);
	}
	
    private AccountDepositRequest setAccountDepositRequest(Double deposit) {
    	Long accountId = 1L;
    	Long transactionTypeId = 1L;
    	Long transactionChannelId = 1L;
    	
    	AccountDepositRequest accountDepositRequest = new AccountDepositRequest();
    	accountDepositRequest.setSourceAccountId(accountId);
    	accountDepositRequest.setTransactionTypeId(transactionTypeId);
    	accountDepositRequest.setTransactionChannelId(transactionChannelId);
    	accountDepositRequest.setAmountToDeposit(deposit);
    	
    	return accountDepositRequest;
    }
    
	@Test
	public void accountDepositCustomerAccountNotFound() {
		AccountDepositRequest accountDepositRequest = this.setAccountDepositRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountDepositRequest.getSourceAccountId())).thenReturn(Optional.empty());
    	Mockito.when(transactionTypeService.getById(accountDepositRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountDepositRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountNotFound.class, () -> bankOperationService.accountDeposit(accountDepositRequest));
	}
	
	@Test
	public void accountDepositTransactionTypeNotFound() {
		AccountDepositRequest accountDepositRequest = this.setAccountDepositRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountDepositRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(0.0)));
    	Mockito.when(transactionTypeService.getById(accountDepositRequest.getTransactionTypeId())).thenReturn(Optional.empty());
    	Mockito.when(transactionChannelService.getById(accountDepositRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(TransactionTypeNotFound.class, () -> bankOperationService.accountDeposit(accountDepositRequest));
	}
	
	@Test
	public void accountDepositTransactionChannelNotFound() {
		AccountDepositRequest accountDepositRequest = this.setAccountDepositRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountDepositRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(0.0)));
    	Mockito.when(transactionTypeService.getById(accountDepositRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountDepositRequest.getTransactionChannelId())).thenReturn(Optional.empty());
    	
    	assertThrows(TransactionChannelNotFound.class, () -> bankOperationService.accountDeposit(accountDepositRequest));
	}
	
	@Test
	public void AccountDepositSucess() {
		CustomerAccount customerAccount = this.getCustomerAccount(0.0);
		Double accountBalance = customerAccount.getAccountBalance();
		
		AccountDepositRequest accountDepositRequest = this.setAccountDepositRequest(50.0);
    	
		Mockito.when(customerAccountService.getById(accountDepositRequest.getSourceAccountId())).thenReturn(Optional.of(customerAccount));
    	Mockito.when(transactionTypeService.getById(accountDepositRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountDepositRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	customerAccount = bankOperationService.accountDeposit(accountDepositRequest);
    	
    	assertEquals(customerAccount.getAccountBalance(),accountBalance + accountDepositRequest.getAmountToDeposit());
	}
	
    private AccountWithdrawRequest setAccountWithdrawRequest(Double amountToWithdraw) {
    	Long accountId = 1L;
    	Long transactionTypeId = 1L;
    	Long transactionChannelId = 1L;
    	
    	AccountWithdrawRequest accountWithdrawRequest = new AccountWithdrawRequest();
    	accountWithdrawRequest.setSourceAccountId(accountId);
    	accountWithdrawRequest.setTransactionTypeId(transactionTypeId);
    	accountWithdrawRequest.setTransactionChannelId(transactionChannelId);
    	accountWithdrawRequest.setAmountToWithdraw(amountToWithdraw);
    	
    	return accountWithdrawRequest;
    }
    
	@Test
	public void accountWithdrawCustomerAccountNotFound() {
		AccountWithdrawRequest accountWithdrawRequest = this.setAccountWithdrawRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountWithdrawRequest.getSourceAccountId())).thenReturn(Optional.empty());
    	Mockito.when(transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountNotFound.class, () -> bankOperationService.accountWithdraw(accountWithdrawRequest));
	}
	
	@Test
	public void accountWithdrawTransactionTypeNotFound() {
		AccountWithdrawRequest accountWithdrawRequest = this.setAccountWithdrawRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountWithdrawRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(50.0)));
    	Mockito.when(transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId())).thenReturn(Optional.empty());
    	Mockito.when(transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(TransactionTypeNotFound.class, () -> bankOperationService.accountWithdraw(accountWithdrawRequest));
	}
	
	@Test
	public void accountWithdrawTransactionChannelNotFound() {
		AccountWithdrawRequest accountWithdrawRequest = this.setAccountWithdrawRequest(50.0);
		
		Mockito.when(customerAccountService.getById(accountWithdrawRequest.getSourceAccountId())).thenReturn(Optional.of(this.getCustomerAccount(50.0)));
    	Mockito.when(transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId())).thenReturn(Optional.empty());
    	
    	assertThrows(TransactionChannelNotFound.class, () -> bankOperationService.accountWithdraw(accountWithdrawRequest));
	}
	
	@Test
	public void AccountWithdrawInsufficientFunds() {
		CustomerAccount customerAccount = this.getCustomerAccount(50.0);
		Double accountBalance = customerAccount.getAccountBalance();
		
		AccountWithdrawRequest accountWithdrawRequest = this.setAccountWithdrawRequest(accountBalance + 0.01);
		
		Mockito.when(customerAccountService.getById(accountWithdrawRequest.getSourceAccountId())).thenReturn(Optional.of(customerAccount));
    	Mockito.when(transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountInsufficientFundsForWithdraw.class, () -> bankOperationService.accountWithdraw(accountWithdrawRequest));
	}
	
	@Test
	public void AccountWithdrawSucess() {
		CustomerAccount customerAccount = this.getCustomerAccount(50.0);
		Double accountBalance = customerAccount.getAccountBalance();
		
		AccountWithdrawRequest accountWithdrawRequest = this.setAccountWithdrawRequest(accountBalance - 0.01);
    	
		Mockito.when(customerAccountService.getById(accountWithdrawRequest.getSourceAccountId())).thenReturn(Optional.of(customerAccount));
    	Mockito.when(transactionTypeService.getById(accountWithdrawRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountWithdrawRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	customerAccount = bankOperationService.accountWithdraw(accountWithdrawRequest);
    	
    	assertEquals(customerAccount.getAccountBalance(),accountBalance - accountWithdrawRequest.getAmountToWithdraw());
	}
	
    private AccountTransferRequest setAccountTransferRequest(Double amountToTransfer) {
    	Long sourceAccountId = 1L;
    	Long targetAccountId = 2L;
    	Long transactionTypeId = 1L;
    	Long transactionChannelId = 1L;
    	
    	AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
    	accountTransferRequest.setSourceAccountId(sourceAccountId);
    	accountTransferRequest.setTargetAccountId(targetAccountId);
    	accountTransferRequest.setTransactionTypeId(transactionTypeId);
    	accountTransferRequest.setTransactionChannelId(transactionChannelId);
    	accountTransferRequest.setAmountToTransfer(amountToTransfer);
    	
    	return accountTransferRequest;
    }
    
	@Test
	public void accountTransferSourceCustomerAccountNotFound() {
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(0.0);
		
		CustomerAccount targetCustomerAccount = this.getCustomerAccount(50.0);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.empty());
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.of(targetCustomerAccount));
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountNotFound.class, () -> bankOperationService.transferBetweenAccounts(accountTransferRequest));
	}
    
	@Test
	public void accountTransferTargetCustomerAccountNotFound() {
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(0.0);
		
		CustomerAccount sourceCustomerAccount = this.getCustomerAccount(50.0);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.of(sourceCustomerAccount));
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.empty());
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountNotFound.class, () -> bankOperationService.transferBetweenAccounts(accountTransferRequest));
	}
    
	@Test
	public void accountTransferTransactionTypeNotFound() {
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(0.0);
		
		CustomerAccount sourceCustomerAccount = this.getCustomerAccount(50.0);
		CustomerAccount targetCustomerAccount = this.getCustomerAccount(50.0);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.of(sourceCustomerAccount));
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.of(targetCustomerAccount));
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.empty());
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(TransactionTypeNotFound.class, () -> bankOperationService.transferBetweenAccounts(accountTransferRequest));
	}
    
	@Test
	public void accountTransferTransactionChannelNotFound() {
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(0.0);
		
		CustomerAccount sourceCustomerAccount = this.getCustomerAccount(50.0);
		CustomerAccount targetCustomerAccount = this.getCustomerAccount(50.0);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.of(sourceCustomerAccount));
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.of(targetCustomerAccount));
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.empty());
    	
    	assertThrows(TransactionChannelNotFound.class, () -> bankOperationService.transferBetweenAccounts(accountTransferRequest));
	}
    
	@Test
	public void accountTransferInsufficientFunds() {
		Double amountToTransfer = 50.0;
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(amountToTransfer);
		
		CustomerAccount sourceCustomerAccount = this.getCustomerAccount(amountToTransfer - 0.01);
		CustomerAccount targetCustomerAccount = this.getCustomerAccount(0.0);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.of(sourceCustomerAccount));
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.of(targetCustomerAccount));
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	assertThrows(CustomerAccountInsufficientFundsForWithdraw.class, () -> bankOperationService.transferBetweenAccounts(accountTransferRequest));
	}
    
	@Test
	public void accountTransferSuccess() {
		Double amountToTransfer = 50.0;
		AccountTransferRequest accountTransferRequest = this.setAccountTransferRequest(amountToTransfer);
		
		Double sourceCustomerAccountBalance = amountToTransfer + 1.0;
		CustomerAccount sourceCustomerAccount = this.getCustomerAccount(sourceCustomerAccountBalance);
		Double targetCustomerAccountBalance = 0.0;
		CustomerAccount targetCustomerAccount = this.getCustomerAccount(targetCustomerAccountBalance);
		
		Mockito.when(customerAccountService.getById(accountTransferRequest.getSourceAccountId())).thenReturn(Optional.of(sourceCustomerAccount));
		Mockito.when(customerAccountService.getById(accountTransferRequest.getTargetAccountId())).thenReturn(Optional.of(targetCustomerAccount));
    	Mockito.when(transactionTypeService.getById(accountTransferRequest.getTransactionTypeId())).thenReturn(Optional.of(this.getTransactionType()));
    	Mockito.when(transactionChannelService.getById(accountTransferRequest.getTransactionChannelId())).thenReturn(Optional.of(this.getTransactionChannel()));
    	
    	bankOperationService.transferBetweenAccounts(accountTransferRequest);
    	
    	assertEquals(sourceCustomerAccount.getAccountBalance(),sourceCustomerAccountBalance - amountToTransfer);
    	assertEquals(targetCustomerAccount.getAccountBalance(),targetCustomerAccountBalance + amountToTransfer);
	}
}