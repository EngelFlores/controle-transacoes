package com.acelera.tcc.group03.business;

import java.util.List;

import com.acelera.tcc.group03.dataload.AgencyDataLoad;
import com.acelera.tcc.group03.dataload.BankDataLoad;
import com.acelera.tcc.group03.dataload.CustomerAccountDataLoad;
import com.acelera.tcc.group03.dataload.CustomerDataLoad;
import com.acelera.tcc.group03.dataload.TransactionChannelDataLoad;
import com.acelera.tcc.group03.dataload.TransactionTypeDataLoad;
import com.acelera.tcc.group03.domains.Agency;
import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.CustomerType;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.domains.TransactionTypeAction;
import com.acelera.tcc.group03.repositories.AgencyRepository;
import com.acelera.tcc.group03.repositories.BankRepository;
import com.acelera.tcc.group03.repositories.CustomerAccountRepository;
import com.acelera.tcc.group03.repositories.CustomerRepository;
import com.acelera.tcc.group03.repositories.TransactionChannelRepository;
import com.acelera.tcc.group03.repositories.TransactionTypeRepository;

public class TransactionSystemApp {
	public static void main(String[] args) {
		BankRepository bankRepository = new BankRepository();
		/*
		for (int i = 0 ; i < BankDataLoad.getBanksData().length; i++) {
			Bank bank = new Bank();
			bank.setName(BankDataLoad.getBanksData()[i][0]);
			bank.setNumber(BankDataLoad.getBanksData()[i][1]);
			bankRepository.create(bank);
		}
		*/
		
		List<Bank> listBanks = bankRepository.readAll();
		listBanks.forEach(bank -> System.out.println(bank.toString()));
		
		CustomerRepository customerRepository = new CustomerRepository();
		/*
		for (int i = 0 ; i < CustomerDataLoad.getCustomersData().length; i++) {
			Customer customer = new Customer();
			customer.setName(CustomerDataLoad.getCustomersData()[i][0]);
			customer.setType(CustomerType.valueOf(CustomerDataLoad.getCustomersData()[i][1]));
			customer.setTin(CustomerDataLoad.getCustomersData()[i][2]);
			customerRepository.create(customer);
		}
		*/
		
		List<Customer> listCustomers = customerRepository.readAll();
		listCustomers.forEach(aCustomer -> System.out.println(aCustomer.toString()));
		
		TransactionChannelRepository transactionChannelRepository = new TransactionChannelRepository();
		/*
		for (int i = 0 ; i < TransactionChannelDataLoad.getTransactionChannelsData().length; i++) {
			TransactionChannel transactionChannel = new TransactionChannel();
			transactionChannel.setName(TransactionChannelDataLoad.getTransactionChannelsData()[i][0]);
			transactionChannelRepository.create(transactionChannel);
		}
		*/
		
		List<TransactionChannel> listTransactionChannels = transactionChannelRepository.readAll();
		listTransactionChannels.forEach(transactionChannel -> System.out.println(transactionChannel.toString()));
		
		TransactionTypeRepository transactionTypeRepository = new TransactionTypeRepository();
		/*
		for (int i = 0 ; i < TransactionTypeDataLoad.getTransactionTypesData().length; i++) {
			TransactionType transactionType = new TransactionType();
			transactionType.setName(TransactionTypeDataLoad.getTransactionTypesData()[i][0]);
			transactionType.setAction(TransactionTypeAction.valueOf(TransactionTypeDataLoad.getTransactionTypesData()[i][1]));
			transactionTypeRepository.create(transactionType);
		}
		*/
		
		List<TransactionType> listTransactionTypes = transactionTypeRepository.readAll();
		listTransactionTypes.forEach(transactionType -> System.out.println(transactionType.toString()));
		
		AgencyRepository agencyRepository = new AgencyRepository();
		/*
		for (int i = 0 ; i < AgencyDataLoad.getAgenciesData().length; i++) {
			Agency agency = new Agency();
			Bank bankOfAgency = bankRepository.findById(new Long(AgencyDataLoad.getAgenciesData()[i][0]));
			agency.setBank(bankOfAgency);
			agency.setName(AgencyDataLoad.getAgenciesData()[i][1]);
			agency.setNumber(AgencyDataLoad.getAgenciesData()[i][2]);
			agencyRepository.create(agency);
		}
		*/
		
		List<Agency> listAgencies = agencyRepository.readAll();
		listAgencies.forEach(agency -> System.out.println(agency.toString()));
		
		CustomerAccountRepository customerAccountRepository = new CustomerAccountRepository();
		/*
		for (int i = 0 ; i < CustomerAccountDataLoad.getCustomerAccountsData().length; i++) {
			CustomerAccount customerAccount = new CustomerAccount();
			
			Customer customerOfAccount = customerRepository.findById(new Long(CustomerAccountDataLoad.getCustomerAccountsData()[i][0]));
			customerAccount.setCustomer(customerOfAccount);
			
			Agency agencyOfAccount = agencyRepository.findById(new Long(CustomerAccountDataLoad.getCustomerAccountsData()[i][1]));
			customerAccount.setAgency(agencyOfAccount);
			
			customerAccount.setAccountBalance(new Double(CustomerAccountDataLoad.getCustomerAccountsData()[i][2]));
			customerAccountRepository.create(customerAccount);
		}
		*/
		
		List<CustomerAccount> listCustomerAccounts = customerAccountRepository.readAll();
		listCustomerAccounts.forEach(customerAccount -> System.out.println(customerAccount.toString()));
	}
}