package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.CustomerAccount;

public class CustomerAccountDAO extends DAO<CustomerAccount> {
	@Override
	protected String getSqlString() {
		return "SELECT ca FROM CustomerAccount ca";
	}
	
	@Override
	protected Class<CustomerAccount> getClassName() {
		return CustomerAccount.class;
	}
}