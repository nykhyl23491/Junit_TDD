package com.app.tdd.stringcalc;

public class NegativeNumberExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final  String message;

	public NegativeNumberExeption(String message) {
		super(message);
		this.message = message;
	}
}
