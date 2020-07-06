package com.acelera.tcc.group03.requests;

public class AccountStatementRequest extends BankOperationRequest {
	private Long amountOfDays;
	
	public Long getAmountOfDays() {
		return this.amountOfDays;
	}
	
	public void setAmountOfDays(Long amountOfDays) {
		this.amountOfDays = amountOfDays;
	}
}