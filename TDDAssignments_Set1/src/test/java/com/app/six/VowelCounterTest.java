package com.app.six;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.exception.InvalidInputException;

public class VowelCounterTest {

	private VowelCounter vowelCounter = new VowelCounter();

	@Test
	public void shouldReturnCountOfVowelsInString() {
		int expected = 6;
		String input = "Lie Audio";
		assertEquals(expected, vowelCounter.getVowelCount(input));
	}

	@Test
	public void shouldReturnZeroWhenStringNotContainVowels() {
		int expected = 0;
		String input = "rhythm shythm";
		assertEquals(expected, vowelCounter.getVowelCount(input));
	}

	@Test(expected = InvalidInputException.class)
	public void shouldThrowExceptionWhenStringIsEmpty() {
		String input = "";
		vowelCounter.getVowelCount(input);
	}

}
