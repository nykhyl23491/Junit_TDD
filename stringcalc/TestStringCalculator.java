package com.app.tdd.stringcalc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStringCalculator {

	StringCalculator stringCalculator = new StringCalculator();

	/*
	 * @Test public void shouldReturnZeroWhenStringIsNull() { int actual =
	 * stringCalculator.calculator(null); assertEquals(0, actual); }
	 */

	@Test
	public void shouldReturnZeroWhenStringIsEmpty() {
		int actual = stringCalculator.calculator("");
		assertEquals(0, actual);
	}

	@Test
	public void shouldReturnNumberItselfWhenStrinHasOnlyOneNumber() {
		int actual = stringCalculator.calculator("3");
		assertEquals(3, actual);
	}

	@Test
	public void shouldReturnAdditionWhenStringHasTwoNumber() {
		int actual = stringCalculator.calculator("2,2");
		assertEquals(4, actual);
	}

	@Test
	public void shouldReturnAdditionWhenStringHasMoreThanTwoNumber() {
		int actual = stringCalculator.calculator("1,2,3,4,5,6,7,8,9");
		assertEquals(45, actual);
	}

	@Test
	public void shouldReturnAdditionWhenStringHasNewLineAsSaparator() {
		int actual = stringCalculator.calculator("1\n3,3");
		assertEquals(7, actual);
	}

	@Test(expected = NegativeNumberExeption.class)
	public void shouldThrowExceptionWhenStringHasNegativeNumber() {
		stringCalculator.calculator("-2,3");
	}
	
	@Test
	public void shouldReturnAdditionWhenStringHasNumberGreaterThan1000() {
		int actual = stringCalculator.calculator("1003,3,5,1001");
		assertEquals(8, actual);
	}

	@Test
	public void shouldReturnAdditionWhenStringHasCustomDelimeter() {
		int actual = stringCalculator.calculator("//;\n1;2\n3;4");
		assertEquals(10, actual);
	} 
 
}

