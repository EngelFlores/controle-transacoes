package com.acelera.tcc.group03.requests;

public class AccountWithdrawRequest extends BankOperationRequest {
	private Double amountToWithdraw;
	
	public Double getAmountToWithdraw() {
		return this.amountToWithdraw;
	}
	
	public void setAmountToWithdraw(Double amountToWithdraw) {
		this.amountToWithdraw = amountToWithdraw;
	}
}