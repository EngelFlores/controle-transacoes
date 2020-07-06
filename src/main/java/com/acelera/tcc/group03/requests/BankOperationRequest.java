package com.acelera.tcc.group03.requests;

public abstract class BankOperationRequest {
	private Long sourceAccountId;
	private Long transactionTypeId;
	private Long transactionChannelId;
	
	public Long getSourceAccountId() {
		return this.sourceAccountId;
	}
	
	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
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