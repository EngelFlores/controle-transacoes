package com.acelera.tcc.group03.exceptions;

public class TransactionTypeNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TransactionTypeNotFound(String errorMessage) {
		super(errorMessage);
	}
}