package com.app.nine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WrongNumberTest {

	private WrongNumber wrongNumber = new WrongNumber();

	@Test
	public void shouldReturnCheckArrayIsNotNull() {
		int arr[] = { 3, 12, 8, 19, 13, 32, 18, 42, 23, 52 };
		int actualResult = wrongNumber.findWrongNumberFromSeries(arr);
		assertEquals(19, actualResult);
	}

	@Test
	public void shouldReturnWrongNumberSuccesfully() {
		int arr[] = { 3, 12, 8, 22, 12, 32, 18, 42, 23, 52 };
		int actualResult = wrongNumber.findWrongNumberFromSeries(arr);
		assertEquals(12, actualResult);
	}

	@Test
	public void shouldReturnZeroIfWrongNumberIsNotFound() {
		int arr[] = { 3, 12, 8, 22, 13, 32, 18, 42, 23, 52 };
		int actualResult = wrongNumber.findWrongNumberFromSeries(arr);
		assertEquals(0, actualResult);
	}

}
