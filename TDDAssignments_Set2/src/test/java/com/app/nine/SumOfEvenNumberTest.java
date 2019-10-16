package com.app.nine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumOfEvenNumberTest {
	
	private SumOfEvenNumber sumOfEvenNumber = new SumOfEvenNumber();
	
	@Test
	public void shouldReturnSumOfEvenNumberInAnArray()
	{
		int arr[] = { 2, 5, 4, 8, 6, 1, 9, 3 };
		int expected =20;
		int actual = sumOfEvenNumber.getSumOfEvenNumber(arr);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnSumZeroWhenArrayDoesNotHaveEvenNumber()
	{
		int arr[] = {1,3,5,7,9};
		int expected =0;
		int actual = sumOfEvenNumber.getSumOfEvenNumber(arr);
		assertEquals(expected, actual);
	}
}
