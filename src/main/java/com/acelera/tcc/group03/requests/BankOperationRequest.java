package com.acelera.tcc.group03.requests;

public class BankOperationRequest {
	private Long accountId;
	private Long transactionTypeId;
	private Long transactionChannelId;
	
	public BankOperationRequest() {
	}
	
	public Long getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Long getTransactionTypeId() {
		return this.transactionTypeId;
	}
	
	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}
	
	public Long getTransactionChannelId() {
		return this.transactionChannelId;
	}
	
	public void setTransactionChannelId(Long transactionChannelId) {
		this.transactionChannelId = transactionChannelId;
	}
}