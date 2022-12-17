package com.masai.app.exceptions;

public class InsufficientBalanceException extends MarrsBankException {

	public InsufficientBalanceException() {
		super();
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
