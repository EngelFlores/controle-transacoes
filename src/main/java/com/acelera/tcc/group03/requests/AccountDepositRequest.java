package com.acelera.tcc.group03.requests;

public class AccountDepositRequest extends BankOperationRequest {
	private Double amountToDeposit;
	
	public Double getAmountToDeposit() {
		return this.amountToDeposit;
	}
	
	public void setAmountToDeposit(Double amountToDeposit) {
		this.amountToDeposit = amountToDeposit;
	}
}