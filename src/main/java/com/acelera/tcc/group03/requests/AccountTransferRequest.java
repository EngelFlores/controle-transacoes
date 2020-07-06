package com.acelera.tcc.group03.requests;

public class AccountTransferRequest extends BankOperationRequest {
	private Long targetAccountId;
	private Double amountToTransfer;
	
	public Long getTargetAccountId() {
		return this.targetAccountId;
	}
	
	public void setTargetAccountId(Long targetAccountId) {
		this.targetAccountId = targetAccountId;
	}
	
	public Double getAmountToTransfer() {
		return this.amountToTransfer;
	}
	
	public void setAmountToTransfer(Double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
}