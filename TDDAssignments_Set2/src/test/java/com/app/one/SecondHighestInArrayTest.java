package com.app.one;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.exception.InvalidInputException;

public class SecondHighestInArrayTest {
	
	private SecondHighestInArray secondHighestInArray = new SecondHighestInArray();
	
	@Test
	public void shouldReturnSecondHighestNumberInArray()
	{
		int arr[] = {22,55,33,88,11,66,44};
		int actual = secondHighestInArray.getSecondHighestNumber(arr);
		int expected = 66;
		assertEquals(expected, actual);
	}
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenInputArrayInNull()
	{
		int arr[] =null;
		secondHighestInArray.getSecondHighestNumber(arr);
	}

}
