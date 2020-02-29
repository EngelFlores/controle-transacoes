package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.TransactionChannel;

public class TransactionChannelRepository extends Repository<TransactionChannel> {
	@Override
	protected String getSqlString() {
		return "SELECT tc FROM TransactionChannel tc";
	}
	
	@Override
	protected Class<TransactionChannel> getClassName() {
		return TransactionChannel.class;
	}
}