package com.acelera.tcc.group03.business;

import java.util.List;

import com.acelera.tcc.group03.dataload.CustomerAccountDataLoad;
import com.acelera.tcc.group03.domains.Agency;
import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.repositories.AgencyDAO;
import com.acelera.tcc.group03.repositories.BankDAO;
import com.acelera.tcc.group03.repositories.CustomerAccountDAO;
import com.acelera.tcc.group03.repositories.CustomerDAO;
import com.acelera.tcc.group03.repositories.TransactionChannelDAO;
import com.acelera.tcc.group03.repositories.TransactionTypeDAO;

public class TransactionSystemApp {
	public static void main(String[] args) {
		BankDAO bankDAO = new BankDAO();
		/*
		for (int i = 0 ; i < BankLoadData.getBanksData().length; i++) {
			Bank bank = new Bank();
			bank.setName(BankLoadData.getBanksData()[i][0]);
			bank.setNumber(BankLoadData.getBanksData()[i][1]);
			bankDAO.create(bank);
		}
		*/
		
		List<Bank> listBanks = bankDAO.readAll();
		listBanks.forEach(bank -> System.out.println(bank.toString()));
		
		CustomerDAO customerDAO = new CustomerDAO();
		/*
		for (int i = 0 ; i < CustomerDataLoad.getCustomersData().length; i++) {
			Customer customer = new Customer();
			customer.setName(CustomerDataLoad.getCustomersData()[i][0]);
			customer.setType(CustomerType.valueOf(CustomerDataLoad.getCustomersData()[i][1]));
			customer.setTin(CustomerDataLoad.getCustomersData()[i][2]);
			customerDAO.create(customer);
		}
		*/
		
		List<Customer> listCustomers = customerDAO.readAll();
		listCustomers.forEach(aCustomer -> System.out.println(aCustomer.toString()));
		
		TransactionChannelDAO transactionChannelDAO = new TransactionChannelDAO();
		/*
		for (int i = 0 ; i < TransactionChannelLoadData.getTransactionChannelsData().length; i++) {
			TransactionChannel transactionChannel = new TransactionChannel();
			transactionChannel.setName(TransactionChannelLoadData.getTransactionChannelsData()[i][0]);
			transactionChannelDAO.create(transactionChannel);
		}
		*/
		
		List<TransactionChannel> listTransactionChannels = transactionChannelDAO.readAll();
		listTransactionChannels.forEach(transactionChannel -> System.out.println(transactionChannel.toString()));
		
		TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAO();
		/*
		for (int i = 0 ; i < TransactionTypeDataLoad.getTransactionTypesData().length; i++) {
			TransactionType transactionType = new TransactionType();
			transactionType.setName(TransactionTypeDataLoad.getTransactionTypesData()[i][0]);
			transactionType.setAction(TransactionTypeAction.valueOf(TransactionTypeDataLoad.getTransactionTypesData()[i][1]));
			transactionTypeDAO.create(transactionType);
		}
		*/
		
		List<TransactionType> listTransactionTypes = transactionTypeDAO.readAll();
		listTransactionTypes.forEach(transactionType -> System.out.println(transactionType.toString()));
		
		AgencyDAO agencyDAO = new AgencyDAO();
		/*
		for (int i = 0 ; i < AgencyDataLoad.getAgenciesData().length; i++) {
			Agency agency = new Agency();
			Bank bankOfAgency = bankDAO.findById(new Long(AgencyDataLoad.getAgenciesData()[i][0]));
			agency.setBank(bankOfAgency);
			agency.setName(AgencyDataLoad.getAgenciesData()[i][1]);
			agency.setNumber(AgencyDataLoad.getAgenciesData()[i][2]);
			agencyDAO.create(agency);
		}
		*/
		
		List<Agency> listAgencies = agencyDAO.readAll();
		listAgencies.forEach(agency -> System.out.println(agency.toString()));
		
		CustomerAccountDAO customerAccountDAO = new CustomerAccountDAO();
		/*
		for (int i = 0 ; i < CustomerAccountDataLoad.getCustomerAccountsData().length; i++) {
			CustomerAccount customerAccount = new CustomerAccount();
			
			Customer customerOfAccount = customerDAO.findById(new Long(CustomerAccountDataLoad.getCustomerAccountsData()[i][0]));
			customerAccount.setCustomer(customerOfAccount);
			
			Agency agencyOfAccount = agencyDAO.findById(new Long(CustomerAccountDataLoad.getCustomerAccountsData()[i][1]));
			customerAccount.setAgency(agencyOfAccount);
			
			customerAccount.setAccountBalance(new Double(CustomerAccountDataLoad.getCustomerAccountsData()[i][2]));
			customerAccountDAO.create(customerAccount);
		}
		*/
		
		List<CustomerAccount> listCustomerAccounts = customerAccountDAO.readAll();
		listCustomerAccounts.forEach(customerAccount -> System.out.println(customerAccount.toString()));	
	}
}