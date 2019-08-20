package com.app.tdd.passwordverifier;

public class PasswordNotVerifyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String message;

	public PasswordNotVerifyException(String message) {
		super(message);
		this.message = message;
	}

}
