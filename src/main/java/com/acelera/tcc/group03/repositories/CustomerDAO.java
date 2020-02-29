package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.Customer;

public class CustomerDAO extends DAO<Customer> {
	@Override
	protected String getSqlString() {
		return "SELECT c FROM Customer c";
	}
	
	@Override
	protected Class<Customer> getClassName() {
		return Customer.class;
	}
}