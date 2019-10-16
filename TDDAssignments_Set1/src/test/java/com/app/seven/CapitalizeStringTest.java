package com.app.seven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.exception.InvalidInputException;

public class CapitalizeStringTest {
	
	private CapitalizeString capitalizeString = new CapitalizeString();
	
	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenInputStringIsEmpty()
	{
		String input ="";
		capitalizeString.convertString(input);
	}
	
	@Test
	public void shouldCapitalizeFirstAndLastLetterOfInputString()
	{
		String input = "yash pune office";
		String expected = "YasH PunE OfficE";
		assertEquals(expected, capitalizeString.convertString(input));
	}

}
