package com.acelera.tcc.group03.exceptions;

public class TransactionChannelNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TransactionChannelNotFound(String errorMessage) {
		super(errorMessage);
	}
}