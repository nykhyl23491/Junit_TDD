package com.app.tcvm.exception;

public class ContainerUnderflowException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String message;

	public ContainerUnderflowException(String message) {
		super(message);
		this.message = message;
	}

}
