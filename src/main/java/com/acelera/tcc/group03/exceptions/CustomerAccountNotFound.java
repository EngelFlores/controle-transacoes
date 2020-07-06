package com.acelera.tcc.group03.exceptions;

public class CustomerAccountNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CustomerAccountNotFound(String errorMessage) {
		super(errorMessage);
	}
}