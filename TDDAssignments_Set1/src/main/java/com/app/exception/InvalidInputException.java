package com.app.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	public InvalidInputException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
