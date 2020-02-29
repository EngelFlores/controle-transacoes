package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.TransactionType;

public class TransactionTypeRepository extends Repository<TransactionType> {
	@Override
	protected String getSqlString() {
		return "SELECT tt FROM TransactionType tt";
	}
	
	@Override
	protected Class<TransactionType> getClassName() {
		return TransactionType.class;
	}
}