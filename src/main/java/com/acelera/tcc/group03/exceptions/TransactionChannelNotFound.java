package com.acelera.tcc.group03.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.BAD_REQUEST, reason = "Transaction Channel not found")
public class TransactionChannelNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TransactionChannelNotFound(String errorMessage) {
		super(errorMessage);
	}
}