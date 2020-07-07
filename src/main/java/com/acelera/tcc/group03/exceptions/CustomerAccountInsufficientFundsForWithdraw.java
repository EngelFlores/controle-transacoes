package com.acelera.tcc.group03.exceptions;

public class CustomerAccountInsufficientFundsForWithdraw extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CustomerAccountInsufficientFundsForWithdraw(String errorMessage) {
		super(errorMessage);
	}
}